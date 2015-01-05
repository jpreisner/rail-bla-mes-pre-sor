package co.project.train;
/*modelisation de la relation association*/
public class Etat {
	private int tranconTete;
	private Direction direction;
	public Etat(int t,Direction d){
		this(d);
		this.tranconTete=t;
	}
	public Etat(Direction d){
		this.direction=d;
		this.tranconTete=0;
	}
	public int getTronconTete() {
		return tranconTete;
	}
	public void deplaceTroncontete(int tronconTete) {
		this.tranconTete += tronconTete;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void setTronconTete(int tronconTete) {
		this.tranconTete = tronconTete;
	}
	
}
