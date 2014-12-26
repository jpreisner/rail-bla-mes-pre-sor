package co.project.train;

public class Train {

	private static int id = 0;
	private int idTrain;
	/* exprimee en nb de troncons */
	private int taille;
	/* exprimee en nombre de troncon par unite de temps */
	private double vitesseMax;
	/* pattern STATE */
	private EtatCourant etatTrain;

	/* position de la tete sur les troncons */

	public Train(int taille, double vitesseMax) {
		this.idTrain = id;
		this.taille = taille;
		this.vitesseMax = vitesseMax;
		id++;
	}

	public int getId() {
		return idTrain;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(double vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	public EtatCourant getEtatTrain() {
		return etatTrain;
	}

	public void setEtatTrain(EtatCourant etatTrain) {
		this.etatTrain = etatTrain;
	}

	@Override
	public String toString() {
		return "Train [ id : " + idTrain + " \n" + "taille : " + taille + " \n" + "Vitesse maximale : " + vitesseMax
				+ " \n" + etatTrain + " ] ";
	}
}
