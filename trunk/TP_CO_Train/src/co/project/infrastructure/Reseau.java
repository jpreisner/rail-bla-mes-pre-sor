package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.infrastructure.rail.Rail;
import co.project.train.EtatCourant;
import co.project.train.Train;

public class Reseau {

	/* Unique instance non initialisee */
	private static Reseau INSTANCE = null;
	private ArrayList<Infrastructure> reseauInfra;
	private ArrayList<Train> matRoulant;

	/**
	 * Constructeur prive
	 */
	private Reseau() {
		this.reseauInfra = new ArrayList<Infrastructure>();
		this.matRoulant = new ArrayList<Train>();
	}

	/**
	 * @return unique point d'acces du singleton reseau
	 */
	public static synchronized Reseau getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Reseau();
		}
		return INSTANCE;
	}

	public ArrayList<Infrastructure> getReseauInfra() {
		return reseauInfra;
	}

	public ArrayList<Train> getMatRoulant() {
		return matRoulant;
	}

	public void addTrain(Train t) {
		matRoulant.add(t);
	}

	public void addPartieReseau(ArrayList<Infrastructure> alInfra) {
		reseauInfra.addAll(alInfra);
	}
	
	public void startTimer(){
		
	}
	public void init(){
		
	}
	
	/**
	 * Fonction permettant le déplacement d'un train dans le réseau
	 * @param train
	 * @throws ErreurJonction
	 * @throws ErreurTrain
	 */
	public void deplacementTrain(Train train) throws ErreurJonction,ErreurTrain
	{
		//Rail ou se trouve la tête du train actuellement
		EtatCourant etatTrain = train.getEtatTrain();
		Rail railcourante = etatTrain.getPositionCouranteTete();
		//On change la position de la tête à la suivante
		
		System.out.println("Train avant : "+train);

		System.out.println("\nDéplacement du train\n");
		
		/* Le train va de gauche à droite dans le réseau*/
		if(etatTrain.isDirectionDroite())
		{
			etatTrain.setPositionCouranteTete( railcourante.getJonctionDroite().getRailSuivant(railcourante));
			//Mise à jour automatique dans setPositionCouranteTete de la positioncourante queue
		}
		/* Le train va de droite à gauche dans le réseau*/
		else if(etatTrain.isDirectionGauche())
		{
			etatTrain.setPositionCouranteTete( railcourante.getJonctionGauche().getRailSuivant(railcourante));
			//Mise à jour automatique dans setPositionCouranteTete de la positioncourante queue
		}
		/* Le train n'a aucune direction*/
		else
		{
			throw new ErreurTrain("Votre train "+train+" n'a aucun sens de déplacement");
		}
	}
	
	
	@Override
	public String toString() {
		String result = "";
		
		for(Infrastructure infra : reseauInfra){
			result += infra ;
		}
		
		for(Train train : matRoulant){
			result += train ;
		}
		
		return result;
	}

}
