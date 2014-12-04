package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Butee extends Jonction {
	private Rail rail;

	public Butee(Rail rail) {
		this.rail = rail;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		throw new ErreurJonction("Fin de la voie");
	}

}
