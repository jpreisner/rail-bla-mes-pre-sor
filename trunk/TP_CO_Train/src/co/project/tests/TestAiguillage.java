package co.project.tests;

import java.util.ArrayList;

import javax.swing.plaf.ButtonUI;

import co.project.ElemRegulation;
import co.project.exception.ErreurCollision;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurSemaphore;
import co.project.exception.ErreurTrain;
import co.project.feu.semaphore.FeuBicolore;
import co.project.feu.semaphore.FeuTricolore;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.FabriqueInfrastructure;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.Reseau;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.Butee;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;
import co.project.train.Train;

public class TestAiguillage {

	public static void main(String[] args) {
		try {
			
			Aiguillage aiguilleY = FabriqueInfrastructure.creeAiguillageY(new FeuBicolore(Direction.GAUCHE));
			
			ElemRegulation element = new ElemRegulation();
			FeuBicolore feuBi = new FeuBicolore(Direction.DROITE);
			try {
				feuBi.setEtatStop();
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
				//Butee buteeFin = new Butee(railFin);
				//railDebut.setJonctionDroite(buteeFin);
				//infra.add(infra.size(), buteeFin);
				
				/**
				 * 
				 */
				//System.out.println("Rail fin : "+railFin.connectable() + " aiguillage " + aiguilleY.getRails().get(0).connectable());
				JonctionSimple j1 = new JonctionSimple(railFin, aiguilleY.getRails().get(0));
				infra.add(infra.size(), j1);
				
				
				//System.out.println("Reseau : "+Reseau.getInstance());
				
				
				
				Reseau.getInstance().addTrain(new Train(14, 2 , (Rail)infra.get(9), Direction.DROITE));
				//Reseau.getInstance().addTrain(new Train(15, 2 , (Rail)infra.get(15), Direction.GAUCHE));
				
				
				
			} catch (ErreurConstruction e) {
				e.printStackTrace();
			}
			
			
			
			ArrayList<Infrastructure> infraAutre = FabriqueInfrastructure.creeSegment(20, 5, 2, element ,new FeuTricolore(Direction.DROITE), new FeuBicolore(Direction.GAUCHE));
			try {
				
				/**
				 * 
				 */
				Rail railDebut = (Rail)infraAutre.get(0);
				JonctionSimple j1 = new JonctionSimple(aiguilleY.getRails().get(1),railDebut);
				infra.add(0, j1);
				
				Butee buteeFinAutre = new Butee((Rail)infraAutre.get(infraAutre.size()-1));
				infra.add(infraAutre.size(),buteeFinAutre);
				
			} catch (ErreurConstruction e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Infrastructure> infraAutre2 = FabriqueInfrastructure.creeSegment(20, 5, 2, element ,new FeuTricolore(Direction.DROITE), new FeuBicolore(Direction.GAUCHE));
			try {
				
				
				/**
				 * 
				 */
				Rail railDebut = (Rail)infraAutre.get(0);
				
				System.out.println("Rail fin 1 : "+railDebut.connectable() + " aiguillage " + aiguilleY.getRails().get(2).connectable());
				JonctionSimple j1 = new JonctionSimple(aiguilleY.getRails().get(2),railDebut);
				infra.add(0, j1);
				
				Butee buteeFinAutre2 = new Butee((Rail)infraAutre2.get(infraAutre2.size()-1));
				infra.add(infraAutre2.size(),buteeFinAutre2);
				
			} catch (ErreurConstruction e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
