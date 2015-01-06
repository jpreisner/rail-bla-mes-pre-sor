package co.project.feu;

public class EtatRalenti extends EtatLimiteCoeff{
	
	protected EtatRalenti(Float coeff) {
		limitation = Double.valueOf(coeff);
	}

}
