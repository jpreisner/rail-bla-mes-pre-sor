package co.project.feu;


public abstract class EtatLimiteCoeff extends EtatSemaphore{
	
	@Override
	public int getVitesse(int vitesse) {
		return Double.valueOf(vitesse*limitation).intValue();
	}
	
}
