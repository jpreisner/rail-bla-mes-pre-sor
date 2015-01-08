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
	 * @param intSema ???
	 * @param elem
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public static ArrayList<Infrastructure> creeSegment(int nbRails, int tRails, int intSema, ElemRegulation elem, Semaphore s1, Semaphore s2) throws CloneNotSupportedException
	{
		ArrayList<Infrastructure> alInfra = new ArrayList<Infrastructure>();
		ArrayList<Rail> alRail = new ArrayList<Rail>();
		ArrayList<JonctionSimple> alJonctionSimple = new ArrayList<JonctionSimple>();
		ArrayList<Semaphore> alSema = new ArrayList<Semaphore>();
		ArrayList<Capteur> alCapteur = new ArrayList<Capteur>();
		try{
			for(int i=0;i<nbRails;i++){
				Rail rail = new Rail(tRails);
				//Semaphore sema = new FeuTricolore(Direction.DROITE);
				
				alRail.add(rail);

			}
			
			/**
			 * Creation des capteurs
			 * Ils sont associe automatiquement a la rail auxquels ils sont attaches
			 */
			CapteurPresence c1 = new CapteurPresence(alRail.get(0), 0);
			CapteurPresence c2 = new CapteurPresence(alRail.get(alRail.size()-1), alRail.get(alRail.size()-1).getLongueur()-1);
		
			
			alCapteur.add(c1);
			alCapteur.add(c2);

			Semaphore s1copie = (Semaphore)s1.clone();
			Semaphore s2copie = (Semaphore)s2.clone();
			
			alRail.get(0).setSemaDroite(s1copie);
			
			alRail.get(alRail.size()-1).setSemaGauche(s2copie);
			
			alSema.add(s1copie);
			alSema.add(s2copie);
			
			for(int i=0;i<nbRails-1;i++){
				alJonctionSimple.add(new JonctionSimple(alRail.get(i),alRail.get(i+1)));
			}
			
			
		}catch(ErreurConstruction ec){
			System.out.println("Erreur dans la fabrication d'un segment simple"+ec);
		}
		elem.ajoutListCapteur(alCapteur);
		elem.ajoutListSemaphores(alSema);
		
		for(int i = 0; i<alRail.size()-1;i++)
		{
			alInfra.add(alRail.get(i));
			alInfra.add(alJonctionSimple.get(i));
		}
		alInfra.add(alRail.get(alRail.size()-1));
		
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
	
	private static void attributionSemaphore(Aiguillage aiguillage, Semaphore sema) throws CloneNotSupportedException
	{
		int i = 0;
		
		while(i<aiguillage.getRails().size()/2)
		{
			aiguillage.getRails().get(i).setJonctionDroite(aiguillage);
			aiguillage.getRails().get(i).setSemaDroite((Semaphore) sema.clone());
			i++;
		}
		
		while(i<aiguillage.getRails().size())
		{
			aiguillage.getRails().get(i).setJonctionGauche(aiguillage);
			aiguillage.getRails().get(i).setSemaGauche((Semaphore) sema.clone());
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
		
		
		for(int i = 0; i<n; i++)
		{
			Rail r = new Rail(tailleRail);
			listRails.add(r);
		}
		
		Aiguillage a = new Aiguillage(listRails);
		attributionSemaphore(a,sema);
		
		a.initialiserFeux();
		
		return a;
	}
	
}
