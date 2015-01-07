package co.project.infrastructure;

public abstract class Infrastructure {

	/* compteur d'instances */
	private static int id = 0;
	protected int idInfrastructure;
	protected int longueur = 0;

	public Infrastructure(int longueur) {
		this.idInfrastructure = id;
		id++;
		this.longueur = longueur;
	}

	public int getIdInfrastructure() {
		return idInfrastructure;
	}

	public int getLongueur() {
		return longueur;
	}
	
	@Override
	public String toString() {
		return "Element Infrastructure [ id : "+idInfrastructure+
				", longueur : "+longueur+" ]\n";
	}
}
