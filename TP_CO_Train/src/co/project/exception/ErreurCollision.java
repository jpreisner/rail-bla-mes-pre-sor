package co.project.exception;

public class ErreurCollision  extends ErreurSignalisation{
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 * quand il y a 2 trains qui passent au meme capteur pendant 1 unite de temps
	 * ou franchissement d'aiguillage impossible
	 */
	public ErreurCollision(String string) {
		super(string);
	}

}
