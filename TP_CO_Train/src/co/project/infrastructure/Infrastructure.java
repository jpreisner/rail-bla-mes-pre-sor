package co.project.infrastructure;

public class Infrastructure {
	protected int longueur = 0; // Longueur de l'element
	protected int idInfrastructure;

	public Infrastructure(int idInfra, int longueur) {
		this.idInfrastructure = idInfra;
		this.longueur = longueur;
	}
	
	@Override
	public String toString() {
		return "[ Element Infrastructure id : "+idInfrastructure+
				"\nlongueur : "+longueur+" ]\n";
	}
}
