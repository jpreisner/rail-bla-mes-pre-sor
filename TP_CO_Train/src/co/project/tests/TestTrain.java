package co.project.tests;

import java.util.ArrayList;

import co.project.infrastructure.FabriqueInfrastructure;
import co.project.infrastructure.Infrastructure;
import co.project.infrastructure.Reseau;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.jonction.JonctionSimple;
import co.project.infrastructure.rail.Rail;
import co.project.train.FabriqueTrain;
import co.project.train.Train;

public class TestTrain {
	
//	public static void main(String[] args) {
//		Rail r1 = FabriqueInfrastructure.creerRail(15);
//		Rail r2 = FabriqueInfrastructure.creerRail(15);
//		JonctionSimple js = FabriqueInfrastructure.connecterDeuxRails(r1, r2);
//		
//		ArrayList<Infrastructure> alInfra = new ArrayList<Infrastructure>();
//		alInfra.add(r1);
//		alInfra.add(js);
//		alInfra.add(r2);
//		Train train = FabriqueTrain.creerTrain(5, 300);
//
//		Reseau.getInstance().addPartieReseau(alInfra);
//		Reseau.getInstance().addTrain(train);
//		
//		System.out.println(r1);
//		System.out.println(r2);
//		System.out.println(js);
//		System.out.println(train);
//	}
	
	/* creation d'aiguillage*/
	public static void main(String[] args) {
		Aiguillage aiguillage = FabriqueInfrastructure.creeAiguillageX();
		
		ArrayList<Infrastructure> alInfra = new ArrayList<Infrastructure>();
		alInfra.add(aiguillage);
		Train train = FabriqueTrain.creerTrain(5, 300);

		Reseau.getInstance().addPartieReseau(alInfra);

		System.out.println(Reseau.getInstance());
	}
	
	/* Arret du train a un aiguillage pour permettre le passage d'un autre train */
//	public static void main(String[] args) {
//		FabriqueInfrastructure.creerRail(15);
//	}
}
