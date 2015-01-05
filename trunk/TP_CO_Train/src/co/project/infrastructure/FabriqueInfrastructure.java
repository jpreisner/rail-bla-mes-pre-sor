package co.project.infrastructure;

import java.util.ArrayList;

import co.project.exception.ErreurConstruction;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.Butee;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;

public class FabriqueInfrastructure {

	/**
	 * Creer une ligne de rails connecté par des jonctions
	 * @param nb
	 * @return
	 */
	public ArrayList<Rail> creeSegment(int nb)
	{
		return null;
	}
	
	/**
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageX() throws ErreurConstruction {
		return creeAiguillageX(10);
	}

	/**
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageY() throws ErreurConstruction {
		return creeAiguillageY(10);
	}

	/**
	 * @return Aiguillage en X, avec 4 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageX(int tailleRail) throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		return new Aiguillage(listRails);
	}

	/**
	 * @return Aiguillage en Y, avec 3 rails connectes, mais sans rails amont ni aval
	 * @throws ErreurConstruction 
	 */
	public static Aiguillage creeAiguillageY(int tailleRail) throws ErreurConstruction {
		ArrayList<Rail> listRails = new ArrayList<Rail>();
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		listRails.add(new Rail(tailleRail));
		return new Aiguillage(listRails);
	}
	
}
