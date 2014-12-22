package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.rail.Rail;

public abstract class Jonction extends Infrastructure {

	public Jonction(int idJonction) {
		super(0, idJonction);
	}

	abstract public Rail getRailSuivant(Rail rail) throws ErreurJonction;
	
	abstract public boolean trainPasse();

}
