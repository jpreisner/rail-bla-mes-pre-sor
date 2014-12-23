package co.project.infrastructure;

public class Infrastructure {
	protected int longueur = 0; // Longueur de l'element
	protected int idInfrastructure;
	private static int id = 0;
	
	public Infrastructure(int longueur) {
		this.idInfrastructure = id;
		id++;
		this.longueur = longueur;
	}
	
	@Override
	public String toString() {
		return "[ Element Infrastructure id : "+idInfrastructure+
				"\nlongueur : "+longueur+" ]\n";
	}
}
