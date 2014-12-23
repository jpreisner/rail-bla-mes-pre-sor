package co.project.capteur;

import co.project.ElemRegulation;

public class CapteurPresence extends Capteur {

	public CapteurPresence(ElemRegulation elm) {
		this.addObserver(elm);
	}

	/*
	 * TODO true si le train passe pendant l'unite de temps courante, false
	 * sinon
	 */
	public boolean trainPassant() {
		notifyObservers();
		return false;
	}

	@Override
	public String toString() {
		return super.toString()+" Presence";
	}
}
