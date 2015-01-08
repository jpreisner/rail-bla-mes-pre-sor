package co.project.feu.semaphore;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.ralenti.EtatOrange;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.train.Direction;

public class FeuTricolore extends Semaphore {

	public FeuTricolore(Direction direction) {
		super(direction);
		etatsPossibles = new EtatSemaphore[]{EtatVert.getInstance(), EtatOrange.getInstance(), EtatRouge.getInstance()};
		etat = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

	@Override
	public void setEtatStop() throws ErreurSemaphore {
		setEtat(EtatRouge.getInstance());
	}

	@Override
	public void setEtatNeutre() throws ErreurSemaphore {
		setEtat(EtatVert.getInstance());
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		FeuTricolore copie = new FeuTricolore(this.direction);
		
		copie.etatsPossibles = this.etatsPossibles.clone();
		copie.etat = this.etat;
		
		return copie;
	}

}
