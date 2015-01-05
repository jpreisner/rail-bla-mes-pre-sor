package co.project.feu;

public final class EtatOrange extends EtatFeu {

	/* Unique instance non initialisee */
	private static EtatOrange INSTANCE = null;

	// Constructeur prive
	private EtatOrange() {
		super("orange");
	}

	/**
	 * @return unique point d'acces du singleton reseau
	 */
	public static synchronized EtatOrange getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new EtatOrange();
		}
		return INSTANCE;
	}

	@Override
	public EtatFeu changeEtat(Semaphore sema) {
		return EtatRouge.getInstance();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
}
