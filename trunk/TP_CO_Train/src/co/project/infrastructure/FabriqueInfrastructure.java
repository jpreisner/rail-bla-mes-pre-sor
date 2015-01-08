package co.project.infrastructure;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.capteur.Capteur;
import co.project.capteur.CapteurPresence;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.feu.etat.coeff.stop.EtatStop;
import co.project.feu.semaphore.FeuTricolore;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;

public class FabriqueInfrastructure {

	private static int TAILLE_RAIL_DEFAUT = 10;

	/**
	 * Creer une ligne de rails connectee par des jonctions simples, sans butee aux extremitees
	 * avec un semaphore tous les n rails
	 * @param nbRails
	 * @param tRails
	 * @param intSema
	 * @param elem
	 * @return
	 */
	public static ArrayList<Infrastructure> creeSegment(int nbRails, int tRails, int intSema, ElemRegulation elem)
	{
		ArrayList<Infrastructure> alInfra = new ArrayList<Infrastructure>();
		ArrayList<Rail> alRail = new ArrayList<Rail>();
		ArrayList<JonctionSimple> alJonctionSimple = new ArrayList<JonctionSimple>();
		ArrayList<Semaphore> alSema = new ArrayList<Semaphore>();
		ArrayList<Capteur> alCapteur = new ArrayList<Capteur>();
		try{
			for(int i=0;i<nbRails;i++){
				Rail rail = new Rail(tRails);
				Semaphore sema = new FeuTricolore(Direction.DROITE);
				alSema.add(sema);
				rail.setSema(sema );
				alRail.add(rail);
				if(i%intSema == 0){
					Capteur capt = new CapteurPresence(rail, tRails/2);
					alCapteur.add(capt);
					/* ajout du capteur au milieu du rail*/
					rail.addCapteurTroncon(tRails/2, capt );
				}
			}		

			for(int i=0;i<nbRails-1;i++){
				alJonctionSimple.add(new JonctionSimple(alRail.get(i),alRail.get(i+1)));
			}
		}catch(ErreurConstruction ec){
			System.out.println("Erreur dans la fabrication d'un segment simple"+ec);
		}
		elem.ajoutListCapteur(alCapteur);
		elem.ajoutListSemaphores(alSema);
		alInfra.addAll(alRail);
		alInfra.addAll(alJonctionSimple);
		return alInfra;
	}
	
	/**
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 * @throws CloneNotSupportedException 
	 */
	public static Aiguillage creeAiguillageX(Semaphore sema) throws ErreurConstruction, CloneNotSupportedException {
		return creeAiguillageX(TAILLE_RAIL_DEFAUT,sema);
	}

	/**
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 * @throws CloneNotSupportedException 
	 */
	public static Aiguillage creeAiguillageY(Semaphore sema) throws ErreurConstruction, CloneNotSupportedException {
		return creeAiguillageY(TAILLE_RAIL_DEFAUT,sema);
	}

	/**
	 * 
	 * @param tailleRail : la taille de chaque rail autour
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction
	 * @throws CloneNotSupportedException 
	 */
	public static Aiguillage creeAiguillageX(int tailleRail, Semaphore sema) throws ErreurConstruction, CloneNotSupportedException {
		return creeAiguillageN(4, tailleRail, sema);
	}
	
	private static void attributionSemaphore(Aiguillage aiguillage, Semaphore sema)
	{
		int i = 0;
		
		while(i<aiguillage.getRails().size()/2)
		{
			aiguillage.getRails().get(i).setJonctionDroite(aiguillage);
			aiguillage.getRails().get(i).setSemaDroite(sema);
			i++;
		}
		
		while(i<aiguillage.getRails().size())
		{
			aiguillage.getRails().get(i).setJonctionGauche(aiguillage);
			aiguillage.getRails().get(i).setSemaGauche(sema);
			i++;
		}
		
	}

	/**
	 * 
	 * @param tailleRail : la taille de chaque rail autour
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction
	 * @throws CloneNotSupportedException 
	 */
	public static Aiguillage creeAiguillageY(int tailleRail,Semaphore sema) throws ErreurConstruction, CloneNotSupportedException {
		return creeAiguillageN(3, tailleRail, sema);
	}
	
	public static Aiguillage creeAiguillageN(int n,int tailleRail, Semaphore sema) throws CloneNotSupportedException, ErreurConstruction
	{
		if(n<2)
			throw new ErreurConstruction("La creation d'un aiguillage necessite d'avoir au moins 3 rails");
		
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		Semaphore tmp = (Semaphore)sema.clone();
		
		for(int i = 0; i<n; i++)
		{
			Rail r = new Rail(tailleRail);
			
			try {
				sema.setEtat(EtatRouge.getInstance());
			} catch (ErreurSemaphore e) {
				System.err.println("Erreur dans la creation de semaphore dans la fabrique : l'aiguillage en X");
			}
			listRails.add(r);
		}
		
		Aiguillage a = new Aiguillage(listRails);
		attributionSemaphore(a,sema);
		
		/**
		 * Passage de l'etat rouge au vert seulement pour les rails connectees
		 */
		try {
			if(a.getRailConnecte1().getJonctionDroite().equals(a))
				a.getRailConnecte1().getSemaDroite().changeEtat();
			else
				a.getRailConnecte1().getSemaGauche().changeEtat();
			
			if(a.getRailConnecte2().getJonctionDroite().equals(a))
				a.getRailConnecte2().getSemaDroite().changeEtat();
			else
				a.getRailConnecte2().getSemaGauche().changeEtat();
			
		} catch (ErreurSemaphore e) {
			System.err.println("Erreur dans la changement d'etat de la semaphore dans la fabrique : l'aiguillage en X");
		}
		
		return a;
	}
	
}
