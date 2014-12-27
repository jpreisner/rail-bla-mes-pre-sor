package co.project.feu;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		etatsPossibles = new EtatFeuEnum[] { EtatFeuEnum.VERT, EtatFeuEnum.ROUGE };
		etatActuel = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etatActuel;
	}
}
