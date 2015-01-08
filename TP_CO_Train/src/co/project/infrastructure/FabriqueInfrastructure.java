package co.project.infrastructure;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.capteur.Capteur;
import co.project.capteur.CapteurPresence;
import co.project.exception.ErreurConstruction;
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
	 */
	public static Aiguillage creeAiguillageX() throws ErreurConstruction {
		return creeAiguillageX(TAILLE_RAIL_DEFAUT);
	}

	/**
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageY() throws ErreurConstruction {
		return creeAiguillageY(TAILLE_RAIL_DEFAUT);
	}

	/**
	 * 
	 * @param tailleRail : la taille de chaque rail autour
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction
	 */
	public static Aiguillage creeAiguillageX(int tailleRail) throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		return new Aiguillage(listRails);
	}

	/**
	 * 
	 * @param tailleRail : la taille de chaque rail autour
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction
	 */
	public static Aiguillage creeAiguillageY(int tailleRail) throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		return new Aiguillage(listRails);
	}
	
}
