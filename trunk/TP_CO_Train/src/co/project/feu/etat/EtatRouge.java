package co.project.feu.etat;

public final class EtatRouge extends EtatStop {

	/* Unique instance non initialisee */
	private static EtatRouge INSTANCE = null;

	// Constructeur prive
	private EtatRouge() {
		super();
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
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
	
	@Override
	public String toString() {
		return "[Rouge]";
	}
}
