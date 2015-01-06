package co.project.infrastructure.jonction;

import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Butee extends Jonction {

	public Butee(Rail rail) throws ErreurConstruction {
		super(0);
		rails.add(rail);
		connecteRailJonction();
	}

	public Rail getRail() {
		return rails.get(0);
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		throw new ErreurJonction("Fin de la voie, depassement de butee");
	}

	@Override
	public boolean trainPassant() {
		return false;
	}

	@Override
	public String toString() {
		return "[B]";
	}

	@Override
	public void connecteRailJonction() throws ErreurConstruction {
		if (!getRail().connectable()) {
			throw new ErreurConstruction("Le rail a deja 2 jonctions a ses extremites, pose de butee impossible");
		} else {
			if (getRail().getJonctionDroite() == null && getRail().getJonctionGauche() == null) {
				getRail().setJonctionGauche(this);
			} else if (getRail().getJonctionDroite() == null && getRail().getJonctionGauche() != null) {
				getRail().setJonctionDroite(this);
			} else {
				getRail().setJonctionGauche(this);
			}
		}
	}
}
