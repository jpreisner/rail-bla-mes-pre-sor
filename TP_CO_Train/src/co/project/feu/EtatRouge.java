package co.project.feu;

public class EtatRouge extends EtatFeu {

	public EtatRouge() {
		// TODO Auto-generated constructor stub
		super("rouge");
	}
	
	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		return new EtatVert();
	}

}
