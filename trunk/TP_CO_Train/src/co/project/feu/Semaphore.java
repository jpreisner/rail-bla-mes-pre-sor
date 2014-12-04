package co.project.feu;

import co.project.exception.ErreurSignalisation;


public abstract class Semaphore {
	
	protected EtatFeu etatActuel;

	public abstract EtatFeuEnum getEtatActuel() throws ErreurSignalisation;
	public abstract void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation;

}
