package co.project.feu;

import co.project.exception.ErreurSemaphore;

public abstract class EtatFeu  {
	
	
	/*
	 * Si le coefficient est a 0 le train s'arrete
	 * S'il est a 1 le train continu sur la meme vitesse 
	 * S'il est entre ]0,1[ cela reduire la vitesse du train
	 */
	protected Float coefficient;
	
	public EtatFeu() {
	}
	
	/**
	 * @param sema
	 * @return l'etat suivant dans du cycle
	 * pour feu Bicolore : VERT -> ROUGE -> VERT
	 * pour le feu Tricolore : VERT -> ORANGE -> ROUGE -> VERT
	 */
	public EtatFeu changeEtat(Semaphore sema) throws ErreurSemaphore
	{
		return sema.getNextEtat();
	}

	public Float getCoefficient() {
		return coefficient;
	}
	
	
	
	
}
