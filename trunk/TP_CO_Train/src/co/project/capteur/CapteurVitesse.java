package co.project.capteur;

import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(Rail rail, int numTronconRail) {
		super(rail, numTronconRail);
	}

	/* FIXME vitesse du train passant sur le troncon */
	public double getVitesse() {
		double vitesseResult = 0;
		for (Train train : getRail().getTrains()) {
			if (train.getEtat().getTronconTete() == getNumTronconRail()) {
				vitesseResult = train.getVitesseCourante();
			}
		}
		return vitesseResult;
	}

	@Override
	public String toString() {
		return super.toString() + " Vitesse";
	}
}
