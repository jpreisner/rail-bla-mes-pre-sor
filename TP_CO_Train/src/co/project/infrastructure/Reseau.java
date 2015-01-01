package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurConstruction;
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
		//Rail ou se trouve la tete du train actuellement
		EtatCourant etatTrain = train.getEtatTrain();
		Rail railcourante = etatTrain.getPositionCouranteTete();
		//On change la position de la tête à la suivante
		
		System.out.println("Train avant : "+train);

		System.out.println("\nDeplacement du train\n");
		
		/* Le train va de gauche a droite dans le reseau*/
		if(etatTrain.isDirectionDroite())
		{
			etatTrain.setPositionCouranteTete( railcourante.getJonctionDroite().getRailSuivant(railcourante));
			//Mise à jour automatique dans setPositionCouranteTete de la positioncourante queue
		}
		/* Le train va de droite a gauche dans le reseau*/
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
	
	
	/**
	 * S'il y a une erreur de construction du reseau
	 * @throws ErreurConstruction
	 */
	
	/**
	 * A COMPLETER
	 */
	public void verifieReseau() throws ErreurConstruction
	{
		
		if(reseauInfra.size()==0)
			throw new ErreurConstruction("Votre reseau est vide");
		else
		{
			for(int i = 0; i < reseauInfra.size(); i++){
				if(reseauInfra.get(i).getClass().getName().equals("Butee")){
					// on incremente i tant qu'on a des rails
					do{
						i++;
					} while(!reseauInfra.get(i).getClass().getName().equals("Rail"));
					
					// Erreur si on retombe directe sur une butee
					if(reseauInfra.get(i).getClass().getName().equals("Butee")){
						// Erreur de type : [B][Rail][B]
						throw new ErreurConstruction("Une portion du reseau est isole entre 2 but�es");
					}
				}
			}
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
