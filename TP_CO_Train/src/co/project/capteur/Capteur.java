package co.project.capteur;

import java.util.Observable;

import co.project.infrastructure.rail.Rail;

public abstract class Capteur extends Observable {
	private Rail rail;
	private int numTronconRail;

	public Capteur(Rail rail, int numTronconRail) {
		this.rail = rail;
		this.numTronconRail = numTronconRail;
	}

	/**
	 * notifie les observeurs si un train passe sur le capteur
	 */
	public void trainPassant() {
		if (rail.getCapteurTroncon().get(numTronconRail).equals(this)) {
			if (rail.trainPassant()) {
				setChanged();
				notifyObservers(this);
			}
		}
	}

	public Rail getRail() {
		return rail;
	}

	public int getNumTronconRail() {
		return numTronconRail;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		try {
			Capteur capt = (Capteur) obj;
			return (capt.rail.equals(rail) && numTronconRail == capt.numTronconRail);
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Capteur";
	}
}
