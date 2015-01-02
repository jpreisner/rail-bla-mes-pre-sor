package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurConstruction;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.Butee;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;

public class FabriqueInfrastructure {

	/**
	 * @param i
	 * @return un rail de i km
	 */
	public static Rail creerRail(int i) {
		return new Rail(i);
	}

	/**
	 * @param r1
	 * @param r2
	 * @return JonctionSimple des 2 rails r1 et r2
	 * @throws ErreurConstruction 
	 */
	public static JonctionSimple connecterDeuxRails(Rail r1, Rail r2) throws ErreurConstruction {
		return new JonctionSimple(r1, r2);
	}

	/**
	 * @param rail
	 * @return Butee connectee a 1 rail
	 * @throws ErreurConstruction 
	 */
	public static Butee connecterUnRail(Rail rail) throws ErreurConstruction {
		return new Butee(rail);
	}

	/**
	 * @param listRails
	 * @return Aiguillage de plusieurs rails
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage connecterPlusieursRails(ArrayList<Rail> listRails) throws ErreurConstruction {
		return new Aiguillage(listRails);
	}

	/**
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageX() throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(creerRail(10));
		listRails.add(creerRail(10));
		listRails.add(creerRail(10));
		listRails.add(creerRail(10));
		return new Aiguillage(listRails);
	}

	/**
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageY() throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(creerRail(10));
		listRails.add(creerRail(10));
		listRails.add(creerRail(10));
		return new Aiguillage(listRails);
	}

}
