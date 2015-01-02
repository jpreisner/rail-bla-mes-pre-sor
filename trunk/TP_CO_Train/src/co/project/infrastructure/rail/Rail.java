package co.project.infrastructure.rail;

import java.util.HashMap;

import co.project.capteur.Capteur;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.jonction.Jonction;

public class Rail extends Infrastructure {
	/* nb de troncons TODO */

	/* 2 jonctions aux extremitees du rail */
	private Jonction gauche;
	private Jonction droite;

	private HashMap<Capteur, Troncon> capteurTroncon;

	public Rail(int longueur) {
		super(longueur);
	}

	public Jonction getJonctionGauche() {
		return gauche;
	}

	public void setJonctionGauche(Jonction j1) {
		this.gauche = j1;
	}

	public Jonction getJonctionDroite() {
		return droite;
	}

	public void setJonctionDroite(Jonction j2) {
		this.droite = j2;
	}

	public HashMap<Capteur, Troncon> getCapteurTroncon() {
		return capteurTroncon;
	}

	public void setCapteurTroncon(HashMap<Capteur, Troncon> capteurNumeroTroncon) {
		this.capteurTroncon = capteurNumeroTroncon;
	}

	public boolean connectable() {
		return (getJonctionDroite() == null || getJonctionGauche() == null);
	}

	/**
	 * @return true/false si le train est sur la jonction
	 */
	@Override
	public boolean trainPasse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		try {
			Rail r = (Rail) obj;
			if (r.idInfrastructure == this.idInfrastructure) {
				return true;
			} else {
				return false;
			}
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "[ Rail id = " + idInfrastructure + " ]";
	}
}
