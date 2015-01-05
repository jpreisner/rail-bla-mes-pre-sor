package co.project.feu;

import java.util.Arrays;
import java.util.Observable;

import co.project.exception.ErreurSemaphore;

public abstract class Semaphore extends Observable{

	protected EtatFeu etat;
	protected EtatFeu[] etatsPossibles;

	// Rouge par defaut au debut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() throws ErreurSemaphore {
		etat = etat.changeEtat(this);
		if(etat == EtatVert.getInstance())
		{
			setChanged();
			notifyObservers();
		}
	}
	
	public EtatFeu getNextEtat() throws ErreurSemaphore
	{
		for(int i = 0; i<etatsPossibles.length; i++)
		{
			if(etat==etatsPossibles[i])
			{
				if(i!=etatsPossibles.length-1)
				{
					return etatsPossibles[i+1];
				}
				else
				{
					return etatsPossibles[0];
				}
			}
				
		}
		
		throw new ErreurSemaphore("Pas d'etat suivant : votre feu n'a qu'un seul etat possible");
	}
}
