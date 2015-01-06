package co.project.feu.etat.coeff;

import co.project.feu.etat.EtatSemaphore;

/**
 * Classe modelisant l'ensemble des semaphore qui impact la vitesse du train en la reduisant ou en la maintenant
 * La vitesse du train dependra de la limitation impose : ici la limitation se doit d'etre comprise dans [0,1]
 * 0 etant les EtatStop
 * 1 etant les EtatNeutre
 * et ]0,1[ les etat ralenti
 *
 */
public abstract class EtatLimiteCoeff extends EtatSemaphore{
	
	/**
	 * Retourne la nouvelle vitesse impactee par le coefficient
	 */
	@Override
	public int getVitesse(int vitesse) {
		return Double.valueOf(vitesse*limitation).intValue();
	}
	
}
