package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.infrastructure.jonction.Jonction;
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
	 * @throws ErreurTrain
	 */
	public void deplacementTrain(Train train) throws ErreurJonction,ErreurTrain
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
				try{
					Rail rail = (Rail) infra;
					if(rail.getJonctionDroite()==null || rail.getJonctionGauche()==null){
						throw new ErreurConstruction("le rail "+rail+", a une jonction manquante");
					}
				}catch(ClassCastException c){
					/* Jonction */
					try{
						Jonction jonction = (Jonction) infra;
						if(jonction.){
							throw new ErreurConstruction("le rail "+jonction+", a une jonction manquante");
						}
					}catch(ClassCastException c){
						/* ne rien faire, c'est une jonction */
					}
				}
			}
			
			/* si un  reseau a un element null*/
			for(Infrastructure infra : reseauInfra){
				if(infra == null){					
					throw new ErreurConstruction("Un element du reseau est null");
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
