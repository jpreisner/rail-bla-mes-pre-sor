package co.project.feu.etat.coeff.stop;

import co.project.feu.etat.coeff.EtatLimiteCoeff;

/**
 * Typiquement des semaphore ordonnant au train de s'arreter
 *
 */
public abstract class EtatStop extends EtatLimiteCoeff{
	
	// Constructeur protected
	protected EtatStop() {
		limitation = Double.valueOf(0);
	}

}
