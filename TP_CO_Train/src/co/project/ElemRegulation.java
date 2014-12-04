package co.project;

import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.feu.Semaphore;
import co.project.infrastructure.rail.Rail;

public class ElemRegulation implements Observer {

	private Capteur capteur;
	private Semaphore feu;
	
	/* action en fonction des informations du capteur*/
	public void ActionSemaphore(){
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void connecterRail(Rail rail1, Rail rail2){
		
	}
}
