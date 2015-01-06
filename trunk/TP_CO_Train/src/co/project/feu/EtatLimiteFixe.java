package co.project.feu;

public abstract class EtatLimiteFixe extends EtatSemaphore{

	@Override
	public int getVitesse(int vitesse) {
		return Double.valueOf(limitation).intValue();
	}
}
