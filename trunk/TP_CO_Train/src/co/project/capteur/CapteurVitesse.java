package co.project.capteur;

import co.project.ElemRegulation;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(ElemRegulation elm) {
		addObserver(elm);
	}

	/* TODO vitesse du train passant sur le tronçon */
	public double getVitesse() {
		return 0;
	}
}
