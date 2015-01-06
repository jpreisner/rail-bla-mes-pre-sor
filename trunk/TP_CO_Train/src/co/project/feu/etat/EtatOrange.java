package co.project.feu.etat;

public final class EtatOrange extends EtatRalenti {

	/* Unique instance non initialisee */
	private static EtatOrange INSTANCE = null;

	// Constructeur prive
	private EtatOrange() {
		super(1f/2);
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
