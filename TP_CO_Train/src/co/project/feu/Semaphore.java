package co.project.feu;

import java.util.Arrays;

import co.project.exception.ErreurSignalisation;

public abstract class Semaphore {

	protected EtatFeuEnum[] etatsPossibles;
	protected EtatFeuEnum etatActuel;

	/**
	 * @return etat actuel du feu
	 * @throws ErreurSignalisation
	 */
	public EtatFeuEnum getEtatActuel() throws ErreurSignalisation {
		return etatActuel;
	}

	/**
	 * @return etats possibles du feu
	 * @throws ErreurSignalisation
	 */
	public EtatFeuEnum[] getEtatsPossibles() throws ErreurSignalisation {
		return etatsPossibles;
	}

	/**
	 * modifie l'etat du feu en passant par l'etat du feu
	 * 
	 * @param EtatFeuEnum
	 * @throws ErreurSignalisation
	 */
	public void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation {
		if (!Arrays.asList(etatsPossibles).contains(etatFeu)) {
			throw new ErreurSignalisation("signalisation impossible pour ce " + this);
		} else {
			this.etatActuel = etatFeu;
		}
	}
}
