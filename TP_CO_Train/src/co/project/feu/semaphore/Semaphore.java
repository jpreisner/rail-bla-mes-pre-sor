package co.project.feu.semaphore;

import java.util.Arrays;
import java.util.Observable;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatSemaphore;
import co.project.train.Direction;

public abstract class Semaphore extends Observable {

	protected EtatSemaphore etat;
	protected EtatSemaphore[] etatsPossibles;
	protected Direction direction;

	public Semaphore(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Passe a l'etat suivant du semaphore selon son cycle
	 * @throws ErreurSemaphore
	 */
	public void changeEtat() throws ErreurSemaphore {
		etat = etat.changeEtat(this);
		setChanged();
		notifyObservers(etat);
	}

	public EtatSemaphore getEtat() {
		return etat;
	}
	
	public Direction getDirection() {
		return direction;
	}

	/**
	 * change d'etat selon l'etat en parametre
	 * @param etat
	 * @throws ErreurSemaphore
	 */
	public void setEtat(EtatSemaphore etat) throws ErreurSemaphore {
		if (Arrays.asList(etatsPossibles).contains(etat)) {
			this.etat = etat;
			setChanged();
			notifyObservers(etat);
		} else {
			throw new ErreurSemaphore("Cet etat n'est pas possible pour le semaphore");
		}
	}

	public EtatSemaphore getNextEtat() throws ErreurSemaphore {
		for (int i = 0; i < etatsPossibles.length; i++) {
			/**
			 * Vu que ce sont des singleton
			 */
			if (etat == etatsPossibles[i]) {
				/**
				 * On est pas a la fin de la liste d'etat possible
				 */
				if (i != etatsPossibles.length - 1) {
					return etatsPossibles[i + 1];
				}
				/**
				 * On retourne a l'etat initial
				 */
				else {
					return etatsPossibles[0];
				}
			}
		}
		throw new ErreurSemaphore("Pas d'etat suivant : votre semaphore n'a qu'un seul etat possible");
	}
	
	/**
	 * Retourne l'etat neutre de la semaphore
	 * @return (null si aucun etat neutre)
	 */
	public abstract EtatSemaphore getEtatNeutre();
	
	/**
	 * Retourne l'etat stop de la semaphore
	 * @return (null si aucun etat neutre)
	 */
	public abstract EtatSemaphore getEtatStop();
	
	/**
	 * Vrai si l'etat actuel est stop
	 * @return
	 */
	public boolean isEtatStop() {
		if(getEtatStop()==null)
			return false;
		return (etat.equals(getEtatStop()));
	}
	/**
	 * Vrai si l'etat actuel est neutre
	 * @return
	 */
	public boolean isEtatNeutre() {
		if(getEtatNeutre()==null)
			return false;
		return (etat.equals(getEtatNeutre()));
	}
	
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
	
	/**
	 * Passe le semaphore a l'etat d'arret
	 */
	public abstract void setEtatStop() throws ErreurSemaphore;
	
	
	/**
	 * Passe le semaphore a l'etat neutre
	 */
	public abstract void setEtatNeutre() throws ErreurSemaphore;
}
