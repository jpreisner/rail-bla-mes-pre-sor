package co.project.capteur;

import java.util.Map.Entry;
import java.util.Observable;

import co.project.exception.ErreurConstruction;
import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

public abstract class Capteur extends Observable {
	private Rail rail;
	private int numTronconRail;

	public Capteur(Rail rail, int numTronconRail) {
		this.rail = rail;
		try {
			this.rail.addCapteurTroncon(this);
		} catch (ErreurConstruction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.numTronconRail = numTronconRail;
	}

	/**
	 * 
	 * @return true/false si un train passe sur le capteur
	 */
	public boolean trainPassant() {
		
		//System.err.println("je suis capteur a " + rail +  "hashMap");
		
		/*for(Entry<Capteur,Integer> entry : rail.getCapteurTroncon().entrySet())
		{
			System.err.println("(key = "+entry.getKey() + ", " + entry.getValue() +  ")");
		}*/
		
		for (Train train : getRail().getTrains()) {
			
			if(estSurCapteur(train))
				return true;
			/*if (train.getEtat().getDirection().equals(Direction.DROITE)) {
				if (train.getEtat().getTronconTete() >= getNumTronconRail()
						&& train.getEtat().getTronconTete() - train.getTaille() <= getNumTronconRail()) {
					return true;
				}
			} else {
				if (train.getEtat().getTronconTete() <= getNumTronconRail()
						&& train.getEtat().getTronconTete() + train.getTaille() >= getNumTronconRail()) {
					return true;
				}
			}*/
		}
		return false;
	}
	
	
	public boolean estSurCapteur(Train t)
	{
		
		
		/**
		 * A l'initialisation le nombre de troncon nous restant
		 * a parcourir est taille du train - la position du troncon courant
		 */
		int troncon = t.getTaille();
		Rail precedente = null;
		boolean continuer = true;
		/**
		 * On parcours tant que notre nombre de troncon est positif 
		 * Et qu'il faut continuer a parcourir les rail
		 */
		while(troncon>=0 && continuer && precedente!=null) {
				/**
				 * On recupere la rail precedente
				 */
				if(precedente==null)
					precedente = t.railPrecedenteDirection(t.getRail());
				else
					precedente = t.railPrecedenteDirection(precedente);
				
				if(precedente==null)
					break;
				
				/**
				 * On dispose de 2 cas
				 * 1) (if) la rail n'a pas la taille suffisante pour 
				 * stocker un nombre de troncon : troncon 
				 * Auquel cas on decremente troncon de la taille de la rail
				 * 
				 * 2) (else) notre rail peut contenir un nombre de troncon : troncon
				 * Auquel cas on s'arrete
				 */
				
				System.err.println("hashMap"+precedente.getCapteurTroncon());
				
				if(precedente.getCapteurTroncon().containsKey(this))
				{
					return true;
				}
				
			
		}
		return false;
	}

	/**
	 * Si un train passe sur le capteur, cette methode notifie tous les observers
	 */
	public void notifieTrainPassant() {
		if (trainPassant()) {
			setChanged();
			notifyObservers(this);
		}
	}

	public Rail getRail() {
		return rail;
	}

	public int getNumTronconRail() {
		return numTronconRail;
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
			Capteur capt = (Capteur) obj;
			return (capt.rail.equals(rail) && numTronconRail == capt.numTronconRail);
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Capteur";
	}
}
