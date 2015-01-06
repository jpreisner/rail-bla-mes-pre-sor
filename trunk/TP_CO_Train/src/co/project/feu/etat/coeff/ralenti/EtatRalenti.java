package co.project.feu.etat.coeff.ralenti;

import co.project.feu.etat.coeff.EtatLimiteCoeff;

/**
 * Typiquement des etat menant au ralentissement de la vitesse du train a travers un coefficient
 *
 */
public class EtatRalenti extends EtatLimiteCoeff{
	
	protected EtatRalenti(Float coeff) {
		limitation = Double.valueOf(coeff);
	}

}
