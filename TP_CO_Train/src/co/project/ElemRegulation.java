package co.project;

import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.feu.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;

public class ElemRegulation implements Observer {

	private Capteur capteur;
	private Semaphore feu;
	private Aiguillage aiguillage;
	
	/* action en fonction des informations du capteur*/
	public void ActionSemaphore(){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
