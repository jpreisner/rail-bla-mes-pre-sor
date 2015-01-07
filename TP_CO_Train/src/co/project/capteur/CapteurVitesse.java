package co.project.capteur;

import co.project.exception.ErreurCapteur;
import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;
import co.project.train.Train;

public class CapteurVitesse extends Capteur {

	public CapteurVitesse(Rail rail, int numTronconRail) {
		super(rail, numTronconRail);
	}

	/**
	 * @return Vitesse du train passant sur le troncon
	 * @throws ErreurCapteur
	 */
	public double getVitesse() throws ErreurCapteur {
		for (Train train : getRail().getTrains()) {
			if(train.getEtat().getDirection().equals(Direction.DROITE)){
				if (train.getEtat().getTronconTete() >= getNumTronconRail() &&
						train.getEtat().getTronconTete()-train.getTaille() <= getNumTronconRail()) {
					return train.getVitesseCourante();
				}
			}else{
				if (train.getEtat().getTronconTete() <= getNumTronconRail() &&
						train.getEtat().getTronconTete()+train.getTaille() >= getNumTronconRail()) {
					return train.getVitesseCourante();
				}
			}
		}
		throw new ErreurCapteur("Le capteur n'a pas de train passant");
	}

	@Override
	public String toString() {
		return super.toString() + " Vitesse";
	}
}
