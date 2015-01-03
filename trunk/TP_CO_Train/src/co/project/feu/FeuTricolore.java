package co.project.feu;

public class FeuTricolore extends Semaphore {

	
	public FeuTricolore() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void changeEtat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Feu Tricolore, " + etat;
	}

	@Override
	public boolean changementEtatPossible(EtatFeu etat) {
		// TODO Auto-generated method stub
		
		//TODO A voir si on autorise passage directe de vert->rouge
		//Je pense que oui
		return true;
	}

}
