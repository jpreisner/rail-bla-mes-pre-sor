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

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		// TODO Auto-generated method stub
		return null;
	}

	//TODO Ne pas bouger aiguillage sur lequel il y a un wagon dessus
	@Override
	public boolean trainPasse() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+
				"Aiguillage a "+lRail.size()+" rails ]";
	}
}
