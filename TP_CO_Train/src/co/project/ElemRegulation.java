package co.project;

import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.exception.ErreurSignalisation;
import co.project.feu.EtatFeuEnum;
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

	public Semaphore getFeu() {
		return feu;
	}

	public Aiguillage getAiguillage() {
		return aiguillage;
	}

	/* action en fonction des informations du capteur */
	public void ActionSemaphore(){
		if(capteur.trainPassant()){
			try {
				getFeu().setEtatActuel(EtatFeuEnum.ROUGE);
			} catch (ErreurSignalisation e) {
			}
		}
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
