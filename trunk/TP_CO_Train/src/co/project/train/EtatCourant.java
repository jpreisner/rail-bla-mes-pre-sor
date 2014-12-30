package co.project.train;

import co.project.infrastructure.rail.Rail;

public class EtatCourant {

	public enum Direction{
		DROITE,
		GAUCHE;
	}
	
	/* position sur le rail */
	private Rail positionCouranteTete,positionCouranteQueue;
	private Direction direction;
	/* vitesse en nombre de troncons par Unite de Temps */
	private int vitesseCourante;
	
	public EtatCourant(Rail positionCourante, Direction direction , int vitesseCourante) {
		this.positionCouranteTete = positionCourante;
		this.vitesseCourante = vitesseCourante;
		this.direction = direction;
	}

	public Rail getPositionCouranteTete() {
		return positionCouranteTete;
	}

	public void setPositionCouranteTete(Rail positionCouranteTete) {
		this.positionCouranteTete = positionCouranteTete;
	}

	public Rail getPositionCouranteQueue() {
		return positionCouranteQueue;
	}

	public void setPositionCouranteQueue(Rail positionCouranteQueue) {
		this.positionCouranteQueue = positionCouranteQueue;
	}
	
	public boolean isDirectionDroite()
	{
		return direction == Direction.DROITE;
	}
	
	public boolean isDirectionGauche()
	{
		return direction == Direction.GAUCHE;
	}
	
	public void changeDirection()
	{
		switch (direction) {
		case DROITE:
			direction = Direction.GAUCHE;
			break;
		case GAUCHE:
			direction = Direction.DROITE;
			break;
		default:
			break;
		}
	}

	public Direction getDirection() {
		return direction;
	}
	
	public int getVitesseCourante() {
		return vitesseCourante;
	}

	public void setVitesseCourante(int vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
	}

	@Override
	public String toString() {
		return "Etat [ Position actuelle : " + positionCouranteTete + " \n" + 
				"\tDirection vers la : " + direction+ " \n" + 
				"\tVitesse courante : " + vitesseCourante+" tr/t ] ";
	}
}
