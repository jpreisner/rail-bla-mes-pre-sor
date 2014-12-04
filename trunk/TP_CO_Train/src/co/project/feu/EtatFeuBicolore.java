package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class EtatFeuBicolore extends EtatFeu {
	private boolean feuVert;
	/* ARRET IMMEDIAT */
	private boolean feuRouge;

	@Override
	public EtatFeuEnum getEtatActuel() throws ErreurSignalisation {
		if (feuVert == feuRouge) {
			throw new ErreurSignalisation("Feu bicolore en erreur");
		} else if (feuVert) {
			return EtatFeuEnum.VERT;
		} else {
			return EtatFeuEnum.ROUGE;
		}
	}

	@Override
	public void setEtatActuel(EtatFeuEnum etat) throws ErreurSignalisation {
		this.feuRouge = false;
		this.feuVert = false;

		if (etat.equals(EtatFeuEnum.ORANGE)) {
			throw new ErreurSignalisation("Feu orange impossible sur un feu bicolore");
		} else if (etat.equals(EtatFeuEnum.VERT)) {
			this.feuVert = true;
		} else {
			this.feuRouge = true;
		}
	}

}
