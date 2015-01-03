package co.project.feu;

public class EtatVert extends EtatFeu{

	public EtatVert() {
		super("vert");
	}

	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		// TODO Auto-generated method stub
		if(sema.changementEtatPossible(new EtatOrange()))
			return new EtatOrange();
		else
			return new EtatRouge();
	}

}
