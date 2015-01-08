package co.project.infrastructure.rail;

import java.util.ArrayList;
import java.util.HashMap;

import co.project.capteur.Capteur;
import co.project.exception.ErreurCollision;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.jonction.Jonction;
import co.project.train.Interval;
import co.project.train.Train;

public class Rail extends Infrastructure {
	/* 2 jonctions aux extremitees du rail */
	private Jonction gauche;
	private Jonction droite;
	private Semaphore sema;

	/* nb de troncons/capteur */
	private HashMap<Integer, Capteur> capteurTroncon;
	
	private ArrayList<Train> trains;
	
	public ArrayList<Train> getTrains() {
		return trains;
	}
	
	public void retirerTrain(Train train)
	{
		trains.remove(train);
	}
	
	public void ajouterTrain(Train train)
	{
		if(!trains.contains(train))
			trains.add(train);
	}

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

	public HashMap<Integer, Capteur> getCapteurTroncon() {
		return capteurTroncon;
	}

	public void setCapteurTroncon(HashMap<Integer, Capteur> capteurNumeroTroncon) {
		this.capteurTroncon = capteurNumeroTroncon;
	}
	

	public Semaphore getSema() {
		return sema;
	}

	public void setSema(Semaphore sema) {
		this.sema = sema;
	}

	/**
	 * @return true/false si le rail est connectable ou pas
	 */
	public boolean connectable() {
		return (getJonctionDroite() == null || getJonctionGauche() == null);
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

	@Override
	public boolean verifierElement() {
		return (getJonctionDroite() != null && getJonctionGauche() != null);
	}
	
	public void testCollisions() throws ErreurCollision{
		
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		
		for (Train train : trains) {
			
			
			
		}
		
	}
}
