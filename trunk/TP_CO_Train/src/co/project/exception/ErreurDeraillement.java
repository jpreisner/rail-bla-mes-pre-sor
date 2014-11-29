package co.project.exception;

public class ErreurDeraillement extends Exception{

	/**
	 * @param string
	 * dépassement de butée
	 */
	public ErreurDeraillement(String string) {
		super(string);
	}

}
