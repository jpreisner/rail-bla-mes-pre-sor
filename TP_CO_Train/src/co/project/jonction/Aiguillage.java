package co.project.jonction;
import java.util.ArrayList;
import java.util.List;

import co.project.ElemRegulation;
import co.project.rail.Rail;


public class Aiguillage extends Jonction {

	/* au moins 3 rails*/
	private List<Rail> lRail;
	
	/* element de régulation*/
	private ElemRegulation elemRegul;
	
	public Aiguillage(ArrayList<Rail> lRail) {
		this.lRail = lRail;
	}
}
