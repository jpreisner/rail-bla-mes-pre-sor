package co.project;

import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.feu.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;

public class ElemRegulation implements Observer {

	private Capteur capteur;
	private Semaphore feu;
	private Aiguillage aiguillage;

	public ElemRegulation(Capteur capteur, Semaphore feu, Aiguillage aiguillage) {
		this.capteur = capteur;
		this.feu = feu;
		this.aiguillage = aiguillage;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}

	public Semaphore getFeu() {
		return feu;
	}

	public void setFeu(Semaphore feu) {
		this.feu = feu;
	}

	public Aiguillage getAiguillage() {
		return aiguillage;
	}
	
	public void setAiguillage(Aiguillage aiguillage) {
		this.aiguillage = aiguillage;
	}

	/* action en fonction des informations du capteur */
	public void ActionSemaphore() {
	}

	/**
	 * 
	 * @param Observable o : observable ayant envoye une notification
	 * @param Object arg : objet notifie (peut etre nulle)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
