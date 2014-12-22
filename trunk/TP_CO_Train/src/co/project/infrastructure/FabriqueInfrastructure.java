package co.project.infrastructure;

import java.util.ArrayList;

import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.Butee;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;

public class FabriqueInfrastructure {

	public static Rail creerRail(int i) {
		return new Rail(1, i);
	}
	
	public static JonctionSimple connecterDeuxRails(Rail r1, Rail r2){
		return new JonctionSimple(2, r1, r2);
	}
	
	public static Butee connecterUnRail(Rail rail){
		return new Butee(3, rail);
	}
	
	public static Aiguillage connecterPlusieursRails(ArrayList<Rail> listRails){
		return new Aiguillage(4, listRails);
	}
	
	public void creer10kmRail() {
		Rail r1 = FabriqueInfrastructure.creerRail(1);
		r1.setJ1(FabriqueInfrastructure.connecterUnRail(r1));
	}
}
