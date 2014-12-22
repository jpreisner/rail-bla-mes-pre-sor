package co.project.infrastructure.jonction;

import java.math.BigInteger;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Butee extends Jonction {
	private Rail rail;

	public Butee(int idJonction, Rail rail) {
		super(idJonction);
		this.rail = rail;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		throw new ErreurJonction("Fin de la voie");
	}

}
