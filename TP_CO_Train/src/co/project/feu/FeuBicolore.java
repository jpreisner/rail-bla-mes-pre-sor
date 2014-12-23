package co.project.feu;

import java.util.Arrays;

import co.project.exception.ErreurSignalisation;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		etatsPossibles = new EtatFeuEnum[] { EtatFeuEnum.VERT, EtatFeuEnum.ROUGE };
		etatActuel = etatsPossibles[0];
	}

	@Override
	public void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation {
		if (!Arrays.asList(etatsPossibles).contains(etatFeu)) {
			throw new ErreurSignalisation("signalisation impossible pour ce feu Bicolore");
		} else {
			this.etatActuel = etatFeu;
		}
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etatActuel;
	}
}
