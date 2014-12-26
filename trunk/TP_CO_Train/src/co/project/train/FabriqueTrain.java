package co.project.train;


public class FabriqueTrain {

	/**
	 * @param taille 
	 * @param vitesseMax 
	 * @param i
	 * @return un train de i wagons
	 */
	public static Train creerTrain(int taille, double vitesseMax) {
		return new Train(taille, vitesseMax);
	}

}
