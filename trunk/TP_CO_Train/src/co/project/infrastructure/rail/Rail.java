package co.project.infrastructure.rail;

import co.project.feu.Semaphore;
import co.project.infrastructure.jonction.Jonction;


public class Rail {
	/* nb de troncons*/
	private int longueur;
	
	/* 2 jonctions aux extrémités du rail */
	private Jonction j1;
	private Jonction j2;
	
	/* 2 feux a chaque extrémité de rail*/
	private Semaphore feu1;
	private Semaphore feu2;
}
