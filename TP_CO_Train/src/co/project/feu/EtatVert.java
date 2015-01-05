package co.project.feu;

public final class EtatVert extends EtatFeu {

	/* Unique instance non initialisee */
	private static EtatVert INSTANCE = null;

	// Constructeur prive
	private EtatVert() {
		super("vert");
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
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
	
	@Override
	public String toString() {
		return "[Vert]";
	}

}
