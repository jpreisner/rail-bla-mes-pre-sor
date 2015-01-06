package co.project;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.project.capteur.CapteurPresence;
import co.project.feu.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;

public class ElemRegulation implements Observer {

	private ArrayList<Aiguillage> listAiguillage;
	private ArrayList<CapteurPresence> listCapteur;
	private ArrayList<Semaphore> listFeu;

	public ElemRegulation(Aiguillage aiguillage) {
		this.listAiguillage =new ArrayList<Aiguillage>() ;
		this.listCapteur = new ArrayList<CapteurPresence>();
		this.listFeu = new ArrayList<Semaphore>();
	}

	public ArrayList<CapteurPresence> getListCapteurs() {
		return listCapteur;
	}

	public ArrayList<Semaphore> getListFeux() {
		return listFeu;
	}

	public ArrayList<Aiguillage> getListAiguillage() {
		return listAiguillage;
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
		// TODO Auto-generated method stub

	}
}
