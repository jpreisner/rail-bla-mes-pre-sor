package co.project.train;

import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Train {
	/* compteur d'instance */
	private static int id = 0;
	private int idTrain;
	/* exprimee en nb de troncons */
	private int taille;
	/* fixe, exprimee en nombre de troncon par unite de temps */
	private int vMax;
	/* vitesse en nombre de troncons par Unite de Temps */
	private int vCourante;
	/* pattern STATE */
	/* private EtatCourant etatTrain; */
	private Rail rail;
	private Etat etat;

	/* position de la tete sur les troncons */

	public Train(int taille, int vMax, Rail rail, Direction direction) {
		this.idTrain = id;
		this.taille = taille;
		this.vMax = vMax;
		this.vCourante = vMax;
		this.etat = new Etat(direction);
		this.rail = rail;
		// this.etatTrain = new EtatCourant(pCourante, direction);
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

	public Rail getRail() {
		return rail;
	}

	public Etat getEtat() {
		return etat;
	}

	/*
	 * public EtatCourant getEtatTrain() { return etatTrain; }
	 */
	@Override
	public String toString() {
		return "\nTrain [ id : " + idTrain + ", taille : " + taille
				+ ", Vitesse maximale : " + vMax + " tr/t ] ";
	}

	public void deplacer() throws ErreurJonction {
		etat.deplaceTroncontete(vCourante);
		
		/**
		 * Représente la différence de taille qu'on a entre la disposition de la
		 * tête du train et que l'extremité de cette dernière
		 */
		int diff = etat.getTronconTete() - rail.getLongueur();
		//On est au bout de la rail donc on passe simplement à la suivante
		if (diff == 0) {
			rail = railSuivanteDirection();
		}
		//On est au delà de la capacité de la rail
		//Un changement de rail est nécessaire
		else if(diff>0)
		{
			/**
			 * On change de rail de facon instantannément
			 * Puis on regarde à travers la boucle
			 * si la rail peut supporter la tête du train
			 */
			
			rail = railSuivanteDirection();
			boolean continuer = true;
			while (continuer) {
				/**
				 * La rail courante est suffisante
				 * On change la disposition de la nouvelle tête
				 * Puis on s'arrête
				 */
				if (rail.getLongueur() > diff) {
					etat.setTronconTete(diff);
					continuer = false;
				}
				/**
				 * Il y a encore du chemin à faire
				 * La rail courante ne suffit pas on va regarder la suivante
				 * On décremente la différence par la taille de la rail suivante
				 */
				else {
					rail = railSuivanteDirection();
					diff -= rail.getLongueur();
				}
			}

		}
	}
	
	private Rail railSuivanteDirection() throws ErreurJonction
	{
		if(etat.getDirection().equals(Direction.DROITE))
			return rail.getJonctionDroite().getRailSuivant(rail);
		else	
			return rail.getJonctionGauche().getRailSuivant(rail);
	}
}
