package co.project.feu.semaphore;

import co.project.exception.ErreurSemaphore;
import co.project.feu.EtatLimiteCoeff;
import co.project.feu.EtatRouge;
import co.project.feu.EtatVert;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		super();
		etatsPossibles = new EtatLimiteCoeff[] { EtatVert.getInstance(), EtatRouge.getInstance()};
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

	public static void main(String[] args) {
		FeuBicolore bico = new FeuBicolore();
		System.out.println(bico.etat);
		try {
			bico.changeEtat();
		} catch (ErreurSemaphore e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bico.etat);

		System.out.println("----");
		
		FeuTricolore bicoTri = new FeuTricolore();
		System.out.println(bicoTri.etat);//Rouge au depart par defaut
		try {
			bicoTri.changeEtat();//Vert
			System.out.println(bicoTri.etat);
			bicoTri.changeEtat();//Orange
			System.out.println(bicoTri.etat);
			
			bicoTri.changeEtat();//Rouge
			System.out.println(bicoTri.etat);
			bicoTri.changeEtat();//Vert
			System.out.println(bicoTri.etat);
		} catch (ErreurSemaphore e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
