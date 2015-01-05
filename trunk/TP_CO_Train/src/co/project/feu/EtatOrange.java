package co.project.feu;

public final class EtatOrange extends EtatFeu {

	/* Unique instance non initialisee */
	private static EtatOrange INSTANCE = null;

	// Constructeur prive
	private EtatOrange() {
		super();
		coefficient = Float.valueOf(1/2);
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
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
	
	@Override
	public String toString() {
		return "[Orange]";
	}
}
