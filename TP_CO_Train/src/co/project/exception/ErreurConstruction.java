package co.project.exception;

public class ErreurConstruction extends Exception{
	private static final long serialVersionUID = 1L;

	public ErreurConstruction() {
		super();
	}
	
	public ErreurConstruction(String message)
	{
		super(message);
	}

}
