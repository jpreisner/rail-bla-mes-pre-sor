package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.rail.Rail;

public abstract class Jonction extends Infrastructure {

	public Jonction(int idJonction) {
		super(idJonction);
	}

	/**
	 * @param rail
	 * @return le rail suivant connecte a la joncton du rail en parametre
	 * @throws ErreurJonction
	 */
	abstract public Rail getRailSuivant(Rail rail) throws ErreurJonction;
	
	/**
	 * @return true/false si le train est sur la jonction
	 */
	abstract public boolean trainPasse();

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+
				"[ Jonction ";
	}
}
