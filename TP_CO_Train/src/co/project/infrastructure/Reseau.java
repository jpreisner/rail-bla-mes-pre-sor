package co.project.infrastructure;

import java.util.ArrayList;

import co.project.train.Train;

public class Reseau {

	private ArrayList<Infrastructure> reseau;
	private ArrayList<Train> roulant;

	public Reseau(ArrayList<Infrastructure> reseau, ArrayList<Train> roulant) {
		this.reseau = reseau;
		this.roulant = roulant;
	}

	public ArrayList<Infrastructure> getReseau() {
		return reseau;
	}

	public ArrayList<Train> getRoulant() {
		return roulant;
	}
	
	public void addTrain(Train t){
		roulant.add(t);
	}
	
	public void addPartieReseau(ArrayList<Infrastructure> alInfra){
		reseau.addAll(alInfra);
	}

}
