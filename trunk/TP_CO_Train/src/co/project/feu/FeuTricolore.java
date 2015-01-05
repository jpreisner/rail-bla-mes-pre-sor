package co.project.feu;

import java.util.Arrays;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		super();
		etatsPossibles = new EtatFeu[]{EtatVert.getInstance(), EtatRouge.getInstance()};
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

//	@Override
//	public boolean changementEtatPossible(EtatFeu etat) {
//		// TODO Auto-generated method stub
//
//		// TODO A voir si on autorise passage directe de vert->rouge
//		// Je pense que oui
//		return Arrays.asList(etatsPossibles).contains(etat);
//	}

}
