package co.project.train;

public class EtatCourant {

	private int positionCourante;
	private boolean sensDeplacement;
	/* vitesse en nombre de troncons par Unite de Temps */
	private int vitesseCourante;
	
	public EtatCourant(int positionCourante, boolean sensDeplacement, int vitesseCourante) {
		this.positionCourante = positionCourante;
		this.sensDeplacement = sensDeplacement;
		this.vitesseCourante = vitesseCourante;
	}

	public int getPositionCourante() {
		return positionCourante;
	}

	public void setPositionCourante(int positionCourante) {
		this.positionCourante = positionCourante;
	}

	public boolean isSensDeplacement() {
		return sensDeplacement;
	}

	public void setSensDeplacement(boolean sensDeplacement) {
		this.sensDeplacement = sensDeplacement;
	}

	public int getVitesseCourante() {
		return vitesseCourante;
	}

	public void setVitesseCourante(int vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
	}

	@Override
	public String toString() {
		return "Etat [ Position actuelle : " + positionCourante + " \n" + 
				"\tSens de la voie : " + sensDeplacement+ " \n" + 
				"\tVitesse courante : " + vitesseCourante+" ] ";
	}
}
