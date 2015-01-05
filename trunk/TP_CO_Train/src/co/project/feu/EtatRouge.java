package co.project.feu;

public final class EtatRouge extends EtatFeu {

	/* Unique instance non initialisee */
	private static EtatRouge INSTANCE = null;

	// Constructeur prive
	private EtatRouge() {
		super("rouge");
	}

	/**
	 * @return unique point d'acces du singleton reseau
	 */
	public static synchronized EtatRouge getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EtatRouge();
		}
		return INSTANCE;
	}

	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		return EtatVert.getInstance();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
}
