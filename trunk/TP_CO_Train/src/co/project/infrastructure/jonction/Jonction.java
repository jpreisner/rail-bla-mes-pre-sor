package co.project.infrastructure.jonction;

import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.rail.Rail;

public abstract class Jonction extends Infrastructure {

	public Jonction(int longueur) {
		super(longueur);
	}

	/**
	 * @param rail
	 * @return le rail suivant connecte a la joncton du rail en parametre
	 * @throws ErreurJonction
	 */
	abstract public Rail getRailSuivant(Rail rail) throws ErreurJonction;
	
	/**
	 * Cette methode, apellee dans le constructeur de la jonction, permet de lier l'extremite du rail a la jonction actuelle
	 * @throws ErreurConstruction
	 */
	abstract public void connecteRailJonction() throws ErreurConstruction;
	
	@Override
	public String toString() {
		return "[Jonction]";
	}
}
