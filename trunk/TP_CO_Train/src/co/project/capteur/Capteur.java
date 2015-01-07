package co.project.capteur;

import java.util.Observable;

import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;
import co.project.train.Train;

public abstract class Capteur extends Observable {
	private Rail rail;
	private int numTronconRail;

	public Capteur(Rail rail, int numTronconRail) {
		this.rail = rail;
		this.numTronconRail = numTronconRail;
	}

	/**
	 * 
	 * @return true/false si un train passe sur le capteur
	 */
	protected boolean trainPassant() {
		for (Train train : getRail().getTrains()) {
			if (train.getEtat().getDirection().equals(Direction.DROITE)) {
				if (train.getEtat().getTronconTete() >= getNumTronconRail()
						&& train.getEtat().getTronconTete() - train.getTaille() <= getNumTronconRail()) {
					return true;
				}
			} else {
				if (train.getEtat().getTronconTete() <= getNumTronconRail()
						&& train.getEtat().getTronconTete() + train.getTaille() >= getNumTronconRail()) {
					return true;
				}
			}
		}
		return false;
	}

	public void notifieTrainPassant() {
		if (trainPassant()) {
			setChanged();
			notifyObservers(this);
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
