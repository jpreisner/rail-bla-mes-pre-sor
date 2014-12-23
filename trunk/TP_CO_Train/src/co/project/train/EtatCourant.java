package co.project.train;

public class EtatCourant {

	private float positionCourante;
	private boolean sensDeplacement;
	/* vitesse en nombre de tronçons par Unité de Temps */
	private double vitesseCourante;

	public float getPositionCourante() {
		return positionCourante;
	}

	public void setPositionCourante(float positionCourante) {
		this.positionCourante = positionCourante;
	}

	public boolean isSensDeplacement() {
		return sensDeplacement;
	}

	public void setSensDeplacement(boolean sensDeplacement) {
		this.sensDeplacement = sensDeplacement;
	}

	public double getVitesseCourante() {
		return vitesseCourante;
	}

	public void setVitesseCourante(double vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
	}

	@Override
	public String toString() {
		return "Etat [ Position actuelle : " + positionCourante + " \n" + 
				"Roule dans le sens de la voie : " + sensDeplacement+ " \n" + 
				"Vitesse courante : " + vitesseCourante+" ] ";
	}
}
