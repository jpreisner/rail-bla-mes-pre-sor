package co.project.train;

import co.project.infrastructure.rail.Rail;

public class FabriqueTrain {

	/**
	 * @param taille
	 * @param direction 
	 * @param vitesseMax
	 * @return un train de (taille) wagons
	 */

	public static Train creerTrain(int taille, int vMax, Rail pCourante, Direction direction) {

		return new Train(taille, vMax, pCourante, direction);
	}

}
