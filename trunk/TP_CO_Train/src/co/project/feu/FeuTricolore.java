package co.project.feu;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		super();
		etatsPossibles = new EtatFeu[]{EtatVert.getInstance(), EtatOrange.getInstance(), EtatRouge.getInstance()};
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

}
