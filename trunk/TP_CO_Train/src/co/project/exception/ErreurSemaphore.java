package co.project.exception;

public class ErreurSemaphore extends Exception{
	private static final long serialVersionUID = 1L;

	public ErreurSemaphore() {
		super();
	}
	
	public ErreurSemaphore(String message) {
		super(message);
	}

}
