package co.project.capteur;

import co.project.ElemRegulation;

public class CapteurPresence extends Capteur {

	public CapteurPresence(ElemRegulation elm) {
		this.addObserver(elm);
	}

	/*
	 * TODO true si le train passe pendant l'unité de temps courante, false
	 * sinon
	 */
	public boolean trainPassant() {
		notifyObservers();
		return false;
	}

}
