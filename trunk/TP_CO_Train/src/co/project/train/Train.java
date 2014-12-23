package co.project.train;

public class Train {

	private int id;
	/* exprimee en nb de troncons */
	private int taille;
	/* exprimee en nombre de troncon par unite de temps */
	private double vitesseMax;
	/* pattern STATE */
	private EtatCourant etatTrain;

	/* position de la tete sur les troncons */

	public Train(int id, int taille, double vitesseMax) {
		this.id = id;
		this.taille = taille;
		this.vitesseMax = vitesseMax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Train [ id : " + id + " \n" + 
				"taille : " + taille+ " \n" + 
				"Vitesse maximale : " + vitesseMax+ " \n" + etatTrain+" ] ";
	}
}
