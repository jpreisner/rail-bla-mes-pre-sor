package co.project.feu.semaphore;

import co.project.feu.EtatLimiteCoeff;
import co.project.feu.EtatOrange;
import co.project.feu.EtatRouge;
import co.project.feu.EtatVert;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		super();
		etatsPossibles = new EtatLimiteCoeff[]{EtatVert.getInstance(), EtatOrange.getInstance(), EtatRouge.getInstance()};
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

}
