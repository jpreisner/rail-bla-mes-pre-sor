package co.project.feu;

import co.project.exception.ErreurSignalisation;

public abstract class Semaphore {

	protected EtatFeu etatActuel;

	/**
	 * @return etat actuel du feu
	 * @throws ErreurSignalisation
	 */
	public abstract EtatFeuEnum getEtatActuel() throws ErreurSignalisation;

	/**
	 * modifie l'état du feu en passant par l'état du feu
	 * @param EtatFeuEnum
	 * @throws ErreurSignalisation
	 */
	public abstract void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation;

}
