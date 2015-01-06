package co.project.feu.etat.fixe;

import co.project.feu.etat.EtatSemaphore;

/**
 * Classe modelisant l'ensemble des semaphores qui pourraient etre des limitateur de vitesse
 * Exemple : un panneau signalant que la vitesse max est de 90 troncon/unite de temps
 *
 */
public abstract class EtatLimiteFixe extends EtatSemaphore{

	/**
	 * Retourne la vitesse imposee
	 */
	@Override
	public int getVitesse(int vitesse) {
		return Double.valueOf(limitation).intValue();
	}
}
