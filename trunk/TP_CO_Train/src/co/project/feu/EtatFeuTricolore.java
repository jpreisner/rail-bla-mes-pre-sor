package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class EtatFeuTricolore extends EtatFeu {

	private boolean feuVert;
	/* VITESSE/2 */
	private boolean feuOrange;
	/* ARRET IMMEDIAT */
	private boolean feuRouge;

	@Override
	public EtatFeuEnum getEtatActuel() throws ErreurSignalisation {
		if (feuVert == feuRouge == feuRouge) {
			throw new ErreurSignalisation("Feu tricolore en erreur");
		} else if (feuVert) {
			return EtatFeuEnum.VERT;
		} else if (feuOrange) {
			return EtatFeuEnum.ORANGE;
		} else {
			return EtatFeuEnum.ROUGE;
		}
	}

	@Override
	public void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation {
		this.feuRouge = false;
		this.feuOrange = false;
		this.feuVert = false;

		if (etatFeu.equals(EtatFeuEnum.VERT)) {
			this.feuVert = true;
		} else if (etatFeu.equals(EtatFeuEnum.ORANGE)) {
			this.feuOrange = true;
		} else {
			this.feuRouge = true;
		}
	}
}
