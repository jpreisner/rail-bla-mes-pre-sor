package co.project.capteur;

import co.project.ElemRegulation;
import co.project.infrastructure.rail.Rail;

public class CapteurPresence extends Capteur {

	public CapteurPresence(Rail rail) {
		super(rail);
	}

	@Override
	public String toString() {
		return super.toString()+" Presence";
	}
}
