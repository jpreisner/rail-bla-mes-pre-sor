package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.rail.Rail;

public abstract class Jonction extends Infrastructure {

	public Jonction() {
		super(0);
	}

	abstract public Rail getRailSuivant(Rail rail) throws ErreurJonction;

}
