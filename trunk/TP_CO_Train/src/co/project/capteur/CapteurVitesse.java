package co.project.capteur;

import co.project.ElemRegulation;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(ElemRegulation elm) {
		addObserver(elm);
	}

	/* FIXME vitesse du train passant sur le tronçon */
	public double getVitesse() {
		notifyObservers(0);
		return 0;
	}
	
	@Override
	public String toString() {
		return super.toString()+" Vitesse";
	}
}
