package co.project.feu;

import java.util.Arrays;

import co.project.exception.ErreurSignalisation;

public abstract class Semaphore {

	protected EtatFeu etatActuel;
	
	//Rouge par defaut
	public Semaphore()
	{
		etatActuel = new EtatRouge();
	}
	
	public abstract void changeEtat();
	
}
