package co.project.exception;

public class ErreurDeraillement extends Exception{
	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 * depassement de butee ou
	 * franchissement d'aiguillage mal configure
	 */
	public ErreurDeraillement(String string) {
		super(string);
	}

}
