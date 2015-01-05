package co.project.train;
/*modelisation de la relation association*/
public class Etat {
	private int trancon_tete;
	private Direction direction;
	public Etat(int t,Direction d){
		this(d);
		this.trancon_tete=t;
	}
	public Etat(Direction d){
		this.direction=d;
		this.trancon_tete=0;
	}
	public int getTronconTete() {
		return trancon_tete;
	}
	public void deplaceTrancontete(int trancon_tete) {
		this.trancon_tete += trancon_tete;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public void setTronconTete(int trancon_tete) {
		this.trancon_tete = trancon_tete;
	}
	
}
