package co.project.feu.semaphore;

import co.project.feu.etat.coeff.EtatLimiteCoeff;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.ralenti.EtatOrange;
import co.project.feu.etat.coeff.stop.EtatRouge;

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
