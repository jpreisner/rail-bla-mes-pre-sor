package co.project.train;

import co.project.infrastructure.rail.Rail;

public class Train {
	/* compteur d'instance*/
	private static int id = 0;
	private int idTrain;
	/* exprimee en nb de troncons */
	private int taille;
	/* fixe, exprimee en nombre de troncon par unite de temps */
	private int vMax;
	/* vitesse en nombre de troncons par Unite de Temps */
	private int vCourante;
	/* pattern STATE */
	private EtatCourant etatTrain;

	/* position de la tete sur les troncons */

	public Train(int taille, int vMax, Rail pCourante, Direction direction) {
		this.idTrain = id;
		this.taille = taille;
		this.vMax = vMax;
		this.vCourante = vMax;
		this.etatTrain = new EtatCourant(pCourante, direction);
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
		return vMax;
	}

	public void setVitesseMax(int vitesseMax) {
		this.vMax = vitesseMax;
	}
	
	public int getVitesseCourante() {
		return vCourante;
	}

	public void setVitesseCourante(int vitesseCourante) {
		this.vCourante = vitesseCourante;
	}
	
	public EtatCourant getEtatTrain() {
		return etatTrain;
	}

	@Override
	public String toString() {
		return "\nTrain [ id : " + idTrain + 
				", taille : " + taille + 
				", Vitesse maximale : " + vMax+  " tr/t ] "+
				" \n" + etatTrain;
	}
}
