package co.project.train;

import co.project.exception.ErreurJonction;
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
	/*private EtatCourant etatTrain;*/
	private Rail rail;
	private Etat etat;

	/* position de la tete sur les troncons */

	public Train(int taille, int vMax, Rail rail, Direction direction) {
		this.idTrain = id;
		this.taille = taille;
		this.vMax = vMax;
		this.vCourante = vMax;
		this.etat=new Etat(direction);
		this.rail=rail;
		//this.etatTrain = new EtatCourant(pCourante, direction);
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

	

	/*public EtatCourant getEtatTrain() {
		return etatTrain;
	}
*/
	@Override
	public String toString() {
		return "\nTrain [ id : " + idTrain + 
				", taille : " + taille + 
				", Vitesse maximale : " + vMax+  " tr/t ] ";
	}
	public void Deplacer() throws ErreurJonction{
		etat.Deplace_Trancon_tete(vCourante);
		switch (etat.getDirection()) {
			case DROITE:
				if (rail.getLongueur()<=etat.getTrancon_tete()){
					int diff=-rail.getLongueur()+etat.getTrancon_tete();
					if (diff==0){
						rail=rail.getJonctionDroite().getRailSuivant(rail);
					}else{
						boolean continuer=true;
						while(continuer){
							if(rail.getLongueur()>diff){
								rail=rail.getJonctionDroite().getRailSuivant(rail);
								etat.setTrancon_tete(diff);
								continuer=false;
							}else{
								rail=rail.getJonctionDroite().getRailSuivant(rail);
								diff-=rail.getLongueur();
							}
						}
						
					}
					
				}
				break;
				//TODO 
			case GAUCHE:
				
			
			break;

		default:
			break;
		}	}
}
