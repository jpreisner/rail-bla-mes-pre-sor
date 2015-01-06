package co.project.capteur;

import co.project.infrastructure.rail.Rail;

public class CapteurPresence extends Capteur {

	public CapteurPresence(Rail rail, int numTronconRail) {
		super(rail,numTronconRail);
	}

	@Override
	public String toString() {
		return super.toString()+" Presence";
	}
}
