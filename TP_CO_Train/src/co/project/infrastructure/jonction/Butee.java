package co.project.infrastructure.jonction;

import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Butee extends Jonction {
	private Rail rail;

	public Butee(Rail rail) throws ErreurConstruction {
		super(0);
		this.rail = rail;
		connecteRailJonction();
	}

	public Rail getRail() {
		return rail;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		throw new ErreurJonction("Fin de la voie, depassement de butee");
	}

	@Override
	public boolean trainPasse() {
		return false;
	}

	@Override
	public String toString() {
		return "[B]";
	}

	@Override
	public void connecteRailJonction() throws ErreurConstruction {
		if (!rail.connectable()) {
			throw new ErreurConstruction("Le rail a deja 2 jonctions a ses extremités, pose de butee impossible");
		} else {
			if (rail.getJonctionDroite() == null && rail.getJonctionGauche() == null) {
				rail.setJonctionGauche(this);
			} else if (rail.getJonctionDroite() == null && rail.getJonctionGauche() != null) {
				rail.setJonctionDroite(this);
			} else {
				rail.setJonctionGauche(this);
			}
		}
	}
}
