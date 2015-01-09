package co.project.tests;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurSemaphore;
import co.project.feu.semaphore.FeuBicolore;
import co.project.feu.semaphore.FeuTricolore;
import co.project.infrastructure.FabriqueInfrastructure;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.Reseau;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.Butee;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;
import co.project.train.Train;

public class TestLigneDroite {


	public static void main(String[] args) {
		try {
			

			ElemRegulation element = new ElemRegulation();
			FeuBicolore feuBi = new FeuBicolore(Direction.DROITE);
			try {
				//feuBi.setEtatStop(); Pour stopper le train
				feuBi.setEtatNeutre();
			} catch (ErreurSemaphore e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<Infrastructure> infra = FabriqueInfrastructure.creeSegment(20, 5, 2, element ,new FeuTricolore(Direction.DROITE), feuBi);
		
			try {
				Rail railDebut = (Rail)infra.get(0);
				Butee buteeDebut = new Butee(railDebut);
				//railDebut.setJonctionGauche(buteeDebut);
				infra.add(0, buteeDebut);
				
				
				Rail railFin = (Rail)infra.get(infra.size()-1);
				Butee buteeFin = new Butee(railFin);
				//railFin.setJonctionDroite(buteeFin);
				infra.add(infra.size(), buteeFin);
				
			} catch (ErreurConstruction e) {
				e.printStackTrace();
				System.exit(2);
			}
		
			Reseau.getInstance().addTrain(new Train(14, 2 , (Rail)infra.get(9), Direction.DROITE));
			Reseau.getInstance().addPartieReseau(infra);
			Reseau.getInstance().verifieReseau();
			
			Reseau.getInstance().start();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErreurConstruction e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ErreurJonction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
