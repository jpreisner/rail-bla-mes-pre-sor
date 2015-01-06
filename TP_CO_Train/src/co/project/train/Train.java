package co.project.train;

import java.util.Observable;
import java.util.Observer;

import co.project.exception.ErreurJonction;
import co.project.feu.etat.coeff.EtatLimiteCoeff;
import co.project.infrastructure.rail.Rail;

public class Train implements Observer{
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

	/**
	 * La methode change la vitesse courante en garantissant qu'on ne depasse pas la vitesse maximale
	 * @param vitesseCourante
	 */
	public void setVitesseCourante(int vitesseCourante) {
		if(vitesseCourante>vMax)
			this.vCourante = vMax;
		else
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

	/**
	 * Fonction du deplacement du train
	 * @throws ErreurJonction
	 */
	//TODO Prendre en compte les semaphores
	public void deplacer() throws ErreurJonction {
		
		if(rail.getSema()!=null)
		{
			//TODO cast force : mettre la vitesse en double
			setVitesseCourante(rail.getSema().getEtat().getVitesse(vCourante));
		}
		
		/*
		 * On se deplace seulement si on a une vitesse > 0
		 */
		if(vCourante>0)
		{
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
						diff -= rail.getLongueur();
						rail = railSuivanteDirection();
					}
				}

			}
		}
	}
	
	/**
	 * Retourne la rail suivante de la rail courante en fonction de la direction
	 * @return la rail suivante de la rail courante en fonction de la direction
	 * @throws ErreurJonction
	 */
	private Rail railSuivanteDirection() throws ErreurJonction
	{
		if(etat.getDirection().equals(Direction.DROITE))
			return rail.getJonctionDroite().getRailSuivant(rail);
		else	
			return rail.getJonctionGauche().getRailSuivant(rail);
	}
	
	public void stop()
	{
		setVitesseCourante(0);
	}
	
	public void start()
	{
		setVitesseCourante(vMax); 
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		try {
			EtatLimiteCoeff etat = (EtatLimiteCoeff)arg;
			
			/**
			 * TODO En fonction de la signalisation ADAPTER la vitesse
			 */
			
			//start();
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this)
			return true;
		if(obj==null)
			return false;
		try {
			Train second = (Train)obj;
			return (this.idTrain == second.getId());
			
		} catch (ClassCastException e) {
			return false;
		}
	}
}
