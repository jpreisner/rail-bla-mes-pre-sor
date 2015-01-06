package co.project;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;

public class ElemRegulation implements Observer {

	private ArrayList<Aiguillage> listAiguillage;
	private ArrayList<Capteur> listCapteur;
	private ArrayList<Semaphore> listFeu;

	public ElemRegulation(Aiguillage aiguillage) {
		this.listAiguillage =new ArrayList<Aiguillage>() ;
		this.listCapteur = new ArrayList<Capteur>();
		this.listFeu = new ArrayList<Semaphore>();
	}

	public ArrayList<Capteur> getListCapteurs() {
		return listCapteur;
	}

	public ArrayList<Semaphore> getListFeux() {
		return listFeu;
	}

	public ArrayList<Aiguillage> getListAiguillage() {
		return listAiguillage;
	}

	public void ajoutObserver(){
		for(Capteur capt : listCapteur){
			capt.addObserver(this);
		}
	}
	
	public void ajoutListCapteur(ArrayList<Capteur> cap)
	{
		listCapteur.addAll(cap);
		ajoutObserver();
	}
	
	/*
	 * action en fonction des informations du capteur 2 cas possibles :
	 * favoritisme systematique d'une des branches du reseau OU systeme FIFO.
	 */
	public void ActionSemaphore(int algo) {
		switch (algo) {
		case 0:
			/* favoritisme 1-2 */
			break;
		case 1:
			/* FIFO */
			break;
		}
	}

	/**
	 * 
	 * @param Observable
	 *            o : observable ayant envoye une notification
	 * @param Object
	 *            arg : objet notifie (peut etre nulle)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		try {
			Capteur capt = (Capteur)arg;
		} catch (ClassCastException e) {
			// TODO: handle exception
		}

	}
}
