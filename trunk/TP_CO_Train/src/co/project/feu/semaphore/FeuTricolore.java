package co.project.feu.semaphore;

import co.project.feu.etat.EtatSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.ralenti.EtatOrange;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.train.Direction;

public class FeuTricolore extends Semaphore {

	public FeuTricolore(Direction direction) {
		super(direction);
		etatsPossibles = new EtatSemaphore[]{EtatVert.getInstance(), EtatOrange.getInstance(), EtatRouge.getInstance()};
		etat = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

}
