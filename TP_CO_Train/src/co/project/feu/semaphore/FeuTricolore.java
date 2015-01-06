package co.project.feu.semaphore;

import co.project.feu.etat.EtatLimiteCoeff;
import co.project.feu.etat.EtatOrange;
import co.project.feu.etat.EtatRouge;
import co.project.feu.etat.EtatVert;

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
