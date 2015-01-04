package co.project.feu;

public class EtatVert extends EtatFeu {

	/* Unique instance non initialisee */
	private static EtatVert INSTANCE = null;

	// Constructeur prive
	private EtatVert() {
		super("rouge");
	}

	/**
	 * @return unique point d'acces du singleton reseau
	 */
	public static synchronized EtatVert getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EtatVert();
		}
		return INSTANCE;
	}

	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		// TODO Auto-generated method stub
		if (sema.changementEtatPossible(EtatOrange.getInstance()))
			return EtatOrange.getInstance();
		else
			return EtatRouge.getInstance();
	}

}
