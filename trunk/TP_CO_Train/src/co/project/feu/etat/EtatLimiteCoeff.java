package co.project.feu.etat;


public abstract class EtatLimiteCoeff extends EtatSemaphore{
	
	@Override
	public int getVitesse(int vitesse) {
		return Double.valueOf(vitesse*limitation).intValue();
	}
	
}
