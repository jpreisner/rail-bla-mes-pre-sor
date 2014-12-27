package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Butee extends Jonction {
	private Rail rail;

	public Butee(Rail rail) {
		super(0);
		this.rail = rail;
	}

	public Rail getRail() {
		return rail;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		throw new ErreurJonction("Fin de la voie, depassement de butee");
	}

	/* */
	@Override
	public boolean trainPasse() {
		return false;
	}

	@Override
	public String toString() {
		return super.toString()+
				"Butee ]";
	}
}
