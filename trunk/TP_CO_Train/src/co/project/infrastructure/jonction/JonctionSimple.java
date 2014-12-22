package co.project.infrastructure.jonction;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class JonctionSimple extends Jonction {

	private Rail rail1;
	private Rail rail2;

	public JonctionSimple(int idJonction, Rail rail1, Rail rail2) {
		super(idJonction);
		this.rail1 = rail1;
		this.rail2 = rail2;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		if (rail.equals(this.rail1)) {
			return this.rail2;
		} else if (rail.equals(this.rail2)) {
			return this.rail1;
		} else {
			throw new ErreurJonction("Erreur dans la récuperation du rail suivant");
		}
	}

	@Override
	public boolean trainPasse() {
		// TODO Auto-generated method stub
		return false;
	}

}
