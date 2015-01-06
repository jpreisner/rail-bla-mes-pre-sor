package co.project.feu.etat;

import co.project.exception.ErreurSemaphore;
import co.project.feu.semaphore.Semaphore;

public abstract class EtatSemaphore {

	/**
	 * @param sema
	 * @return l'etat suivant dans du cycle
	 * pour feu Bicolore : VERT -> ROUGE -> VERT
	 * pour le feu Tricolore : VERT -> ORANGE -> ROUGE -> VERT
	 */
	public EtatSemaphore changeEtat(Semaphore sema) throws ErreurSemaphore
	{
		return sema.getNextEtat();
	}
	
	/*
	 * Si le coefficient est a 0 le train s'arrete
	 * S'il est a 1 le train continu sur la meme vitesse 
	 * S'il est entre ]0,1[ cela reduire la vitesse du train
	 */
	protected Double limitation;

	public Double getLimitation() {
		return limitation;
	}
	
	/**
	 * Classe retournant une vitesse a partir de la limitation instaure
	 * Pour plus d'informations : EtatLimiteFixe et EtatLimiteCoeff
	 * @param vitesse : l'ancienne vitesse
	 * @return : la nouvelle vitesse (typiquement il se pourrait qu'elle soit egale a l'ancienne vitesse)
	 */
	public abstract int getVitesse(int vitesse);
}
