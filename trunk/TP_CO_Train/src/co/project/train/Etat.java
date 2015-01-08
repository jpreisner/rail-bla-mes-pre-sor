package co.project.train;

/**
 * modelisation de la relation association
 */
public class Etat {
	private int tronconTete;
	private Direction direction;

	public Etat(int t, Direction d) {
		this(d);
		this.tronconTete = t;
	}

	public Etat(Direction d) {
		this.direction = d;
		this.tronconTete = 0;
	}

	public int getTronconTete() {
		return tronconTete;
	}

	public void deplaceTroncontete(int tronconTete) {
		if(direction == Direction.DROITE)
			this.tronconTete += tronconTete;
		else
			this.tronconTete -= tronconTete;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setTronconTete(int tronconTete) {
		this.tronconTete = tronconTete;
	}

	@Override
	public String toString() {
		return "[troncon =" + tronconTete + " Direction = " + direction + "]";
	}
}
