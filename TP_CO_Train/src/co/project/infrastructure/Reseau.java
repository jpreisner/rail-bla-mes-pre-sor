package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurCollision;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

public final class Reseau {

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

	/**
	 * 
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
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
	/**
	 * Mettre les trains au bon endroit sur les rails
	 */
	public void init(){
		
	}
	
	/**
	 * Fonction permettant le déplacement d'un train dans le réseau
	 * @param train
	 * @throws ErreurJonction
	 * @throws ErreurCollision
	 */
	public void deplacementTrain(Train train) throws ErreurJonction,ErreurCollision
	{
		train.deplacer();
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
			/* si un rail a une jonction manquante */
			for(Infrastructure infra : reseauInfra){
				if(!infra.verifierElement())
					throw new ErreurConstruction("Element "+infra+" est mal construit");
			}
			
			/* si un  reseau a un element null*/
			for(Infrastructure infra : reseauInfra){
				if(infra == null){					
					throw new ErreurConstruction("Un element du reseau est null");
				}
			}

		}
	}
	
	public void deplaceTousTrains(){
		for (Train train : matRoulant) {
			try {
				deplacementTrain(train);
			} catch (ErreurJonction | ErreurCollision e) {
				
			}
		}
	}
	
	/**
	 * Pour tester si dans le reseau, il y a une collision entre les differents trains et celui en parametre
	 * @param train 
	 * @throws ErreurCollision
	 * @throws ErreurJonction 
	 */
	public void testCollisions(Train train) throws ErreurCollision, ErreurJonction{		
		//FIXME faire une methode de simulation : on projette un train vers sa futur destination :
		//et on regarde si sa tete ou sa queue est en collision avec un autre train
		//FIXME non correcte : verifie qu'il sont sur la meme rail : s'il y a deux trains sens contraire
						
		/* collision de face*/
		testCollisionFace(train);
				
		/* collision en tete a queue*/	
		testCollisionQueue(train);

	}
	
	private void testCollisionQueue(Train train) {
		// TODO Auto-generated method stub
		
	}

	private void testCollisionFace(Train train) throws ErreurCollision, ErreurJonction {

		for (Train train2 : matRoulant) { 
			if(!train.equals(train2)){
				if(train.getRail().equals(train2.getRail())){
					/* cas 1 : meme rail (un troncon separe les 
					 * 2 trains ou pas de troncon separe les 2 trains*/
					if(Math.abs(train.getEtat().getTronconTete()-train2.getEtat().getTronconTete())<=2){
						throw new ErreurCollision("Collision de face entre le train : "+train+
							"\n et le train : "+train2);
					}
				}else{
					/* cas 2 : rail differente */
					if(train.railSuivanteDirection().equals(train2.getRail())){
						if(train.getRail().getLongueur() == train.getEtat().getTronconTete() &&
								train2.getEtat().getTronconTete()==0){
							/* pas de troncon qui separe les 2 trains */
							throw new ErreurCollision("Collision de face entre le train : "+train+
									"\n et le train : "+train2);
						}else if(isTrainSepare(train, train2) || isTrainSepare(train2, train))
						{
							throw new ErreurCollision("Collision de face entre le train : "+train+
									"\n et le train : "+train2);
						}
					}
				}
			}
		}		
		
	}
	
	private boolean isTrainSepare(Train train, Train train2)
	{
		return (train.getRail().getLongueur()-1 == train.getEtat().getTronconTete() && 
				train2.getEtat().getTronconTete()==0);
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
