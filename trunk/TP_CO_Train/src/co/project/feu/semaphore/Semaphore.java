package co.project.feu.semaphore;

import java.util.Observable;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatRouge;
import co.project.feu.etat.EtatSemaphore;

public abstract class Semaphore extends Observable{

	protected EtatSemaphore etat;
	protected EtatSemaphore[] etatsPossibles;

	// Rouge par defaut au debut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() throws ErreurSemaphore {
		etat = etat.changeEtat(this);
		setChanged();
		notifyObservers(etat);
	}
	
	
	
	public EtatSemaphore getEtat() {
		return etat;
	}

	public EtatSemaphore getNextEtat() throws ErreurSemaphore
	{
		for(int i = 0; i<etatsPossibles.length; i++)
		{
			/**
			 * Vu que ce sont des singleton
			 */
			if(etat==etatsPossibles[i])
			{
				/**
				 * On est pas a la fin de la liste d'etat possible
				 */
				if(i!=etatsPossibles.length-1)
				{
					return etatsPossibles[i+1];
				}
				/**
				 * On retourne a l'etat initial
				 */
				else
				{
					return etatsPossibles[0];
				}
			}
				
		}
		
		throw new ErreurSemaphore("Pas d'etat suivant : votre semaphore n'a qu'un seul etat possible");
	}
}
