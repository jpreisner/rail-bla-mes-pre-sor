package co.project.feu;

public abstract class Semaphore {

	protected EtatFeu etat;

	// Rouge par defaut
	public Semaphore() {
		etat = EtatRouge.getInstance();
	}

	public void changeEtat() {
		etat = etat.changeEtat(this);
	}

	public abstract boolean changementEtatPossible(EtatFeu etat);
}
