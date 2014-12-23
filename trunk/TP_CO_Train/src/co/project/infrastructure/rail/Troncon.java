package co.project.infrastructure.rail;

import co.project.capteur.Capteur;

public class Troncon {
	private Capteur capteur;

	public Troncon(Capteur capteur) {
		this.capteur = capteur;
	}

	public Capteur getCapteur() {
		return capteur;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+
				"[ Troncon ]";
	}
}
