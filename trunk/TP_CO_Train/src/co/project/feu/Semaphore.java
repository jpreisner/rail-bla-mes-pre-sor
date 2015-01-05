package co.project.feu;

import java.util.Arrays;
import java.util.Observable;

public abstract class Semaphore extends Observable{

	protected EtatFeu etat;
	protected EtatFeu[] etatsPossibles;

	// Rouge par defaut au debut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() {
		etat = etat.changeEtat(this);
		if(etat == EtatVert.getInstance())
		{
			setChanged();
			notifyObservers();
		}
	}

	public boolean changementEtatPossible(EtatFeu etat){
		return Arrays.asList(etatsPossibles).contains(etat);
	}
}
