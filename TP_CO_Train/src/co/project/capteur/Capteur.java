package co.project.capteur;

import java.util.Observable;

import co.project.infrastructure.rail.Rail;

public abstract class Capteur extends Observable {

	private Rail rail;
	
	public Capteur(Rail rail) {
		this.rail = rail;
	}
	
	/**
	 * @return true/false si le train passe sur le capteur
	 */
	public void trainPassant()
	{
		if(rail.trainPassant())
		{
			setChanged();
			notifyObservers(this);
		}
	}
	
	public Rail getRail() {
		return rail;
	}

	@Override
	public String toString() {
		return "Capteur";
	}
}
