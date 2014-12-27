package co.project.train;

public class FabriqueTrain {

	/**
	 * @param taille
	 * @param vitesseMax
	 * @return un train de (taille) wagons
	 */

	public static Train creerTrain(int taille, int vMax) {
		return new Train(taille, vMax, 0, true, vMax);
	}

}
