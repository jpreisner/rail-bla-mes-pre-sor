package co.project.feu;

public class EtatOrange extends EtatFeu {

	public EtatOrange() {
		super("orange");
	}
	
	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		return new EtatRouge();
	}

}
