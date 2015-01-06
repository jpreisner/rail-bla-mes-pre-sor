package co.project.feu.etat.coeff.neutre;

import co.project.feu.etat.coeff.EtatLimiteCoeff;

/**
 * Semaphore n'impactant pas la vitesse du train
 * Typiquement un Feu Vert
 *
 */
public class EtatNeutre extends EtatLimiteCoeff {

	/**
	 * Constructeur
	 */
	protected EtatNeutre() {
		limitation = 1.0;
	}
}
