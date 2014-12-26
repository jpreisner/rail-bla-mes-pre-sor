package co.project.infrastructure.jonction;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class Aiguillage extends Jonction {

	/* au moins 3 rails */
	private ArrayList<Rail> lRail;
	/* element de regulation */
	private ElemRegulation elemRegul;
	private Rail railConnecte1;
	private Rail railConnecte2;

	public Aiguillage(int idJonction, ArrayList<Rail> lRail) {
		super(idJonction);
		this.lRail = lRail;
	}

	public ArrayList<Rail> getlRail() {
		return lRail;
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
	
	public void setRailConnecte1(Rail r1) {
		this.railConnecte1 = r1;
	}
	
	public void setRailConnecte2(Rail r2) {
		this.railConnecte2 = r2;
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		return (rail.equals(railConnecte1)) ? railConnecte2 : railConnecte1;
	}

	// TODO Ne pas bouger aiguillage sur lequel il y a un wagon dessus
	@Override
	public boolean trainPasse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "Aiguillage a " + lRail.size() + " rails ]";
	}
}
