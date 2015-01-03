package co.project.feu;

import java.util.Arrays;

import co.project.exception.ErreurSignalisation;

public abstract class Semaphore {

	protected EtatFeu etat;
	
	//Rouge par defaut
	public Semaphore()
	{
		etat = new EtatRouge();
	}
	
	public abstract void changeEtat();
	
	public abstract boolean changementEtatPossible(EtatFeu etat);
}
