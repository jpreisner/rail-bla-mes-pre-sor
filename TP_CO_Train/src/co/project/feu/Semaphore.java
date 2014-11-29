package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class Semaphore {
	
	private EtatFeu EtatActuel;

	public EtatFeu getEtatActuel() throws ErreurSignalisation{
		return EtatActuel;
	}
}
