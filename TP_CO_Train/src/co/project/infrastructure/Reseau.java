package co.project.infrastructure;

import java.util.ArrayList;

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
