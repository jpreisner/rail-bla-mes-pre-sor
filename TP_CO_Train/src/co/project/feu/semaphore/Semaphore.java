package co.project.feu.semaphore;

import java.util.Observable;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatLimiteCoeff;
import co.project.feu.etat.EtatRouge;

public abstract class Semaphore extends Observable{

	protected EtatLimiteCoeff etat;
	protected EtatLimiteCoeff[] etatsPossibles;

	// Rouge par defaut au debut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() throws ErreurSemaphore {
		etat = etat.changeEtat(this);
		setChanged();
		notifyObservers(etat);
	}
	
	
	
	public EtatLimiteCoeff getEtat() {
		return etat;
	}

	public EtatLimiteCoeff getNextEtat() throws ErreurSemaphore
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
