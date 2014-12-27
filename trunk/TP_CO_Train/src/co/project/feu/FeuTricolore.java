package co.project.feu;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		etatsPossibles = new EtatFeuEnum[] { EtatFeuEnum.VERT, EtatFeuEnum.ORANGE, EtatFeuEnum.ROUGE };
		etatActuel = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etatActuel;
	}
}
