package co.project.train;

import co.project.infrastructure.rail.Rail;

public class FabriqueTrain {

	/**
	 * @param taille
	 * @param vitesseMax
	 * @return un train de (taille) wagons
	 */

	public static Train creerTrain(int taille, int vMax, Rail pCourante) {

		return new Train(taille, vMax, pCourante, true, vMax);
	}

}
