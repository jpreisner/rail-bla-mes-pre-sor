package co.project.capteur;

import java.util.Observable;

public abstract class Capteur extends Observable {

	/**
	 * @return true/false si le train passe sur le capteur
	 */
	public abstract boolean trainPassant();

	@Override
	public String toString() {
		return "Capteur";
	}
}
