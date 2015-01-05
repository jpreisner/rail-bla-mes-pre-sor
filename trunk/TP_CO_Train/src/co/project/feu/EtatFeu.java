package co.project.feu;

import co.project.exception.ErreurSemaphore;

public abstract class EtatFeu  {
	
	protected String name;
	
	public EtatFeu(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	/**
	 * @param sema
	 * @return l'�tat suivant dans du cycle
	 * pour feu Bicolore : VERT -> ROUGE -> VERT
	 * pour le feu Tricolore : VERT -> ORANGE -> ROUGE -> VERT
	 */
	public EtatFeu changeEtat(Semaphore sema) throws ErreurSemaphore
	{
		return sema.getNextEtat();
	}

	public String getName() {
		return name;
	}
	
	
}
