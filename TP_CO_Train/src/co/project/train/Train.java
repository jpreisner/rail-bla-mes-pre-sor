package co.project.train;

import co.project.infrastructure.rail.Rail;

public class Train {

	/* compteur d'instance*/
	private static int id = 0;
	private int idTrain;
	/* exprimee en nb de troncons */
	private int taille;
	/* fixe, exprimee en nombre de troncon par unite de temps */
	private int vitesseMax;
	/* pattern STATE */
	private EtatCourant etatTrain;

	/* position de la tete sur les troncons */

	public Train(int taille, int vMax, Rail pCourante, boolean sensDep, int vCourante) {
		this.idTrain = id;
		this.taille = taille;
		this.vitesseMax = vMax;
		this.etatTrain = new EtatCourant(pCourante, EtatCourant.Direction.DROITE, vCourante);
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

	public int getVitesseMax() {
		return vitesseMax;
	}

	public void setVitesseMax(int vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	public EtatCourant getEtatTrain() {
		return etatTrain;
	}

	@Override
	public String toString() {
		return "Train [ id : " + idTrain + 
				", taille : " + taille + 
				", Vitesse maximale : " + vitesseMax+  " ] "+
				" \n" + etatTrain;
	}
}
