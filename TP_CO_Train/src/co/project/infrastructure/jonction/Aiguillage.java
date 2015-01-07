package co.project.infrastructure.jonction;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.exception.ErreurAiguillage;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

public class Aiguillage extends Jonction {

	/* au moins 3 rails */

	/* element de regulation */
	private ElemRegulation elemRegul;
	/* rails connectes de l'aiguillage */
	private Rail railConnecte1;
	private Rail railConnecte2;

	public Aiguillage(ArrayList<Rail> rails) throws ErreurConstruction {
		super(0);
		this.rails = rails;
		railConnecte1 = this.rails.get(0);
		railConnecte2 = this.rails.get(1);
		initRailJonction();
		initialiserFeux();
	}

	public ArrayList<Rail> getrails() {
		return rails;
	}

	public ElemRegulation getElemRegul() {
		return elemRegul;
	}

	public Rail getRailConnecte1() {
		return railConnecte1;
	}

	public Rail getRailConnecte2() {
		return railConnecte2;
	}

	public void setRailConnecte1(Rail r1) throws ErreurAiguillage {
		if (trainPassant()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r1);
		} else {
			this.railConnecte1 = r1;
		}
	}

	public void setRailConnecte2(Rail r2) throws ErreurAiguillage {
		if (trainPassant()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r2);
		} else {
			this.railConnecte2 = r2;
		}
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		return (rail.equals(railConnecte1)) ? railConnecte2 : railConnecte1;
	}

	/**
	 * @return true/false si un train est sur l'aiguilage
	 */
	public boolean trainPassant() {
		for (Train train : railConnecte1.getTrains()) {
			if(railConnecte2.getTrains().contains(train)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		// return super.toString() + "Aiguillage a " + rails.size() +
		// " rails ]";
		String listeRailNumero = "";
		for (Rail r : rails) {
			listeRailNumero += r.getIdInfrastructure() + ",";
		}
		return "[A -" + rails.size() + " directions : rails : " + listeRailNumero + "]";
	}

	@Override
	public void initRailJonction() throws ErreurConstruction {
		for (Rail rail : rails) {
			if (!rail.connectable()) {
				throw new ErreurConstruction("le rail : " + rail + ", n'est pas connectable a l'aiguillage.");
			}
		}
		for (Rail rail : rails) {
			if (rail.getJonctionGauche() == null) {
				rail.setJonctionGauche(this);
			} else {
				rail.setJonctionDroite(this);
			}
		}
	}

	/**
	 * Cette methode permet de mettre tous les feux des rails de l'aiguillage a
	 * rouge , sauf le railconnecte1 a vert .
	 */
	private void initialiserFeux() {
		/* railconnecte1 au vert */
		try {
			if(railConnecte1.getSema()!=null){
			railConnecte1.getSema().setEtatNeutre();

			/* autres rails au rouge */
			for (Rail rail : rails) {
				if (!rail.equals(railConnecte1)) {
					rail.getSema().setEtatStop();
				}
			}
			}
		} catch (ErreurSemaphore e) {
			System.out.println("Initialisation des semaphores de l'aiguillage impossible");
		}
	}
	
	/**
	 * @param Rail r1
	 * @param Rail r2
	 * @throws ErreurAiguillage
	 */
	public void changementAiguillage(Rail r1, Rail r2) throws ErreurAiguillage{
		if(!rails.contains(r1) || !rails.contains(r2)){
			throw new ErreurAiguillage("un des rails passees en parametres ne sont pas dans l'aiguillage");
		}
		else{
			
			setRailConnecte1(r1);
			setRailConnecte2(r2);
		}
	}

	@Override
	public boolean verifierElement() {
		if(rails.size()<3){
			return false;
		}
		return(getRailConnecte1() != null && getRailConnecte2() != null);
		
		//FIXME verifie qu'elle ont toute une etat stop
	}
}
