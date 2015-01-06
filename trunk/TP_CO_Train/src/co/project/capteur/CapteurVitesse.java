package co.project.capteur;

import co.project.infrastructure.rail.Rail;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(Rail rail) {
		super(rail);
	}

	/* FIXME vitesse du train passant sur le troncon */
	public double getVitesse() {
		setChanged();
		notifyObservers(0);
		return 0;
	}
	
	@Override
	public String toString() {
		return super.toString()+" Vitesse";
	}
}
