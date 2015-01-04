package co.project.feu;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void changeEtat() {
		// TODO Auto-generated method stub
		super.changeEtat();
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

	@Override
	public boolean changementEtatPossible(EtatFeu etat) {
		// TODO Auto-generated method stub

		if (etat.getName().equals("orange"))
			return false;

		return true;
	}

	public static void main(String[] args) {
		FeuBicolore bico = new FeuBicolore();

		System.out.println("Changement possible ? " + bico.changementEtatPossible(EtatRouge.getInstance()));
	}

}
