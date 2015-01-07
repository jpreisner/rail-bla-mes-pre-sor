package co.project.feu.semaphore;

import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.EtatSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.train.Direction;

public class FeuBicolore extends Semaphore {

	public FeuBicolore(Direction direction) {
		super(direction);
		etatsPossibles = new EtatSemaphore[] { EtatVert.getInstance(), EtatRouge.getInstance()};
		etat = etatsPossibles[0];
	}

	@Override
	public String toString() {
		return "Feu Bicolore, " + etat;
	}

	@Override
	public void setEtatStop() throws ErreurSemaphore {
		setEtat(EtatRouge.getInstance());
	}

	@Override
	public void setEtatNeutre() throws ErreurSemaphore {
		setEtat(EtatVert.getInstance());
	}
	
	public static void main(String[] args) {
		FeuBicolore bico = new FeuBicolore(Direction.DROITE);
		System.out.println(bico.etat);
		try {
			bico.changeEtat();
		} catch (ErreurSemaphore e) {
			System.out.println(e.getMessage());
		}
		System.out.println(bico.etat);

		System.out.println("----");
		
		FeuTricolore bicoTri = new FeuTricolore(Direction.GAUCHE);
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
			System.out.println(e.getMessage());
		}
		
		
	}

}
