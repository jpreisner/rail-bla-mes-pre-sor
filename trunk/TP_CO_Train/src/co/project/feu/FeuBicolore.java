package co.project.feu;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		super();
		etatsPossibles = new EtatFeu[] { EtatVert.getInstance(), EtatRouge.getInstance() };
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

	public static void main(String[] args) {
		FeuBicolore bico = new FeuBicolore();
		System.out.println(bico.etat);
		bico.changeEtat();
		System.out.println(bico.etat);
		System.out.println("Changement orange possible ? " + bico.changementEtatPossible(EtatOrange.getInstance()));
		System.out.println("Changement vert possible ? " + bico.changementEtatPossible(EtatVert.getInstance()));
	}

}
