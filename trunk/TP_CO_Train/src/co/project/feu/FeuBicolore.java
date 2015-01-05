package co.project.feu;

import java.util.Arrays;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		super();
		etatsPossibles = new EtatFeu[] { EtatVert.getInstance(), EtatRouge.getInstance() };
	}

	@Override
	public void changeEtat() {
		super.changeEtat();
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

//	@Override
//	public boolean changementEtatPossible(EtatFeu etat) {
//		return Arrays.asList(etatsPossibles).contains(etat);
//	}

	public static void main(String[] args) {
		FeuBicolore bico = new FeuBicolore();

		System.out.println("Changement orange possible ? " + bico.changementEtatPossible(EtatOrange.getInstance()));
		System.out.println("Changement vert possible ? " + bico.changementEtatPossible(EtatVert.getInstance()));
	}

}
