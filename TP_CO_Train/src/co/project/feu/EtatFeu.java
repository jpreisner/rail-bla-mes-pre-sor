package co.project.feu;

import co.project.exception.ErreurSignalisation;

public abstract class EtatFeu {

	public abstract EtatFeuEnum getEtatActuel() throws ErreurSignalisation;

	public abstract void setEtatActuel(EtatFeuEnum etat) throws ErreurSignalisation;

}
