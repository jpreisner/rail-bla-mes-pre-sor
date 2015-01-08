package co.project.feu.semaphore;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.train.Direction;

public class FeuBicolore extends Semaphore {

	public FeuBicolore(Direction direction) {
		super(direction);
		etatsPossibles = new EtatSemaphore[] { EtatVert.getInstance(), EtatRouge.getInstance()};
		etat = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

	@Override
	public void setEtatStop() throws ErreurSemaphore {
		setEtat(EtatRouge.getInstance());
	}

	@Override
	public void setEtatNeutre() throws ErreurSemaphore {
		setEtat(EtatVert.getInstance());
	}

}
