package co.project.feu;

import java.util.Arrays;

public abstract class Semaphore {

	protected EtatFeu etat;
	protected EtatFeu[] etatsPossibles;

	// Rouge par defaut au debut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() {
		etat = etat.changeEtat(this);
	}

	public boolean changementEtatPossible(EtatFeu etat){
		return Arrays.asList(etatsPossibles).contains(etat);
	}
}
