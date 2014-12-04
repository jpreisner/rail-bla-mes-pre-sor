package co.project.infrastructure.rail;

import java.math.BigInteger;
import java.util.HashMap;

import co.project.capteur.Capteur;
import co.project.feu.Semaphore;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.jonction.Jonction;


public class Rail extends Infrastructure{
	private static BigInteger idRail;
	/* nb de troncons*/
	private int longueur;
	
	/* 2 jonctions aux extrémités du rail */
	private Jonction j1;
	private Jonction j2;

	private HashMap<Capteur, Integer> capteurNumeroTroncon;
	
	public Rail(int longueur) {
		this.longueur = longueur;
	}
	
	public void setJ1(Jonction j1){
		this.j1 = j1;
	}
	
	public void setJ2(Jonction j2){
		this.j2 = j2;
	}
}
