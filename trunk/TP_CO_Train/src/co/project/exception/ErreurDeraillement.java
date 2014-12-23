package co.project.exception;

public class ErreurDeraillement extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 * depassement de butee
	 */
	public ErreurDeraillement(String string) {
		super(string);
	}

}
