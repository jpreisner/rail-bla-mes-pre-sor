package co.project.feu.etat;

public class EtatRalenti extends EtatLimiteCoeff{
	
	protected EtatRalenti(Float coeff) {
		limitation = Double.valueOf(coeff);
	}

}
