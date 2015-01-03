package co.project.infrastructure.jonction;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.capteur.CapteurPresence;
import co.project.exception.ErreurAiguillage;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Aiguillage extends Jonction {

	/* au moins 3 rails */

	/* element de regulation */
	private ElemRegulation elemRegul;
	/* rails connectes de l'aiguillage */
	private Rail railConnecte1;
	private Rail railConnecte2;

	public Aiguillage(ArrayList<Rail> rails) throws ErreurConstruction {
		super(0);
		this.rails = rails;
		railConnecte1 = this.rails.get(0);
		railConnecte2 = this.rails.get(1);
		connecteRailJonction();
	}

	public ArrayList<Rail> getrails() {
		return rails;
	}

	public ElemRegulation getElemRegul() {
		return elemRegul;
	}

	public Rail getRailConnecte1() {
		return railConnecte1;
	}

	public Rail getRailConnecte2() {
		return railConnecte2;
	}

	public void setRailConnecte1(Rail r1) throws ErreurAiguillage {
		if (trainPasse()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r1);
		} else {
			this.railConnecte1 = r1;
		}
	}

	public void setRailConnecte2(Rail r2) throws ErreurAiguillage {
		if (trainPasse()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r2);
		} else {
			this.railConnecte2 = r2;
		}
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		return (rail.equals(railConnecte1)) ? railConnecte2 : railConnecte1;
	}

	@Override
	public boolean trainPasse() {
		for (CapteurPresence capt : elemRegul.getListCapteurs()) {
			if (capt.trainPassant()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		// return super.toString() + "Aiguillage a " + rails.size() +
		// " rails ]";
		String listeRailNumero = "";
		for (Rail r : rails) {
			listeRailNumero += r.getIdInfrastructure() + ",";
		}
		return "[A -" + rails.size() + " directions : rails : " + listeRailNumero + "]";
	}

	@Override
	public void connecteRailJonction() throws ErreurConstruction {
		for (Rail rail : rails) {
			if (!rail.connectable()) {
				throw new ErreurConstruction("le rail : " + rail + ", n'est pas connectable a l'aiguillage.");
			}
		}
		for (Rail rail : rails) {
			if (rail.getJonctionGauche() == null) {
				rail.setJonctionGauche(this);
			} else {
				rail.setJonctionDroite(this);
			}
		}

	}
}
