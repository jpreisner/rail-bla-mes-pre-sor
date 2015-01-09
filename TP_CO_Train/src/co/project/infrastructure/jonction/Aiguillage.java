package co.project.infrastructure.jonction;

import java.util.ArrayList;

import co.project.ElemRegulation;
import co.project.exception.ErreurAiguillage;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurSemaphore;
import co.project.feu.etat.coeff.neutre.EtatVert;
import co.project.feu.etat.coeff.stop.EtatRouge;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

/**
 * au moins 3 rails
 */
public class Aiguillage extends Jonction {
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

	public ArrayList<Rail> getRails() {
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

	/**
	 * Connecte le rail1 a l'aiguillage
	 * @param r1
	 * @throws ErreurAiguillage
	 */
	private void setRailConnecte1(Rail r1) throws ErreurAiguillage {
		if (trainPassant()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r1);
		} else {
			this.railConnecte1 = r1;
		}
	}

	/**
	 * Connecte le rail2 a l'aiguillage
	 * @param r2
	 * @throws ErreurAiguillage
	 */
	private void setRailConnecte2(Rail r2) throws ErreurAiguillage {
		if (trainPassant()) {
			throw new ErreurAiguillage("changement d'aiguillage impossible sur le rail : " + r2);
		} else {
			this.railConnecte2 = r2;
		}
	}

	@Override
	public Rail getRailSuivant(Rail rail) {
		return (rail.equals(railConnecte1)) ? railConnecte2 : railConnecte1;
	}

	/**
	 * @return true/false si un train est sur l'aiguilage
	 */
	public boolean trainPassant() {
		for (Train train : railConnecte1.getTrains()) {
			if(railConnecteEstOccupee(train, railConnecte2))
			{
				return true;
			}
		}
		
		for (Train train : railConnecte2.getTrains()) {
			if(railConnecteEstOccupee(train, railConnecte1))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public boolean railConnecteEstOccupee(Train t, Rail r)
	{
		/**
		 * A l'initialisation le nombre de troncon nous restant
		 * a parcourir est taille du train - la position du troncon courant
		 */
		int troncon = t.getTaille() - t.getEtat().getTronconTete();
		Rail precedente = null;
		boolean continuer = true;
		/**
		 * On parcours tant que notre nombre de troncon est positif 
		 * Et qu'il faut continuer a parcourir les rail
		 */
		while(troncon>0 && continuer) {
				/**
				 * On recupere la rail precedente
				 */
				if(precedente==null)
					precedente = t.railPrecedenteDirection(t.getRail());
				else
					precedente = t.railPrecedenteDirection(precedente);
				
				/**
				 * On dispose de 2 cas
				 * 1) (if) la rail n'a pas la taille suffisante pour 
				 * stocker un nombre de troncon : troncon 
				 * Auquel cas on decremente troncon de la taille de la rail
				 * 
				 * 2) (else) notre rail peut contenir un nombre de troncon : troncon
				 * Auquel cas on s'arrete
				 */
				
				if(precedente.equals(r))
				{
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
	public void initialiserFeux() {
		/* railconnecte1 au vert */
		try {
			
			Semaphore semaRail1 = railConnecte1.getSemaDroite();
			if(semaRail1!=null)
			{
				if(semaRail1.getEtatNeutre()!=null)
				{
					if(getRailConnecte1().getJonctionDroite().equals(this))
						getRailConnecte1().getSemaDroite().setEtatNeutre();
					else
						getRailConnecte1().getSemaGauche().setEtatNeutre();
				}
			}
			
			Semaphore semaRail2 = railConnecte2.getSemaGauche();
			if(semaRail2!=null)
			{
				if(semaRail2.getEtatNeutre()!=null)
				{
					if(getRailConnecte2().getJonctionDroite()!=null && getRailConnecte2().getJonctionDroite().equals(this))
						getRailConnecte2().getSemaDroite().setEtatNeutre();
					else if(getRailConnecte2().getJonctionGauche()!=null && getRailConnecte2().getJonctionGauche().equals(this))
						getRailConnecte2().getSemaGauche().setEtatNeutre();
				}
			}
			
			
			
			for(Rail rail : rails)
			{
				if(!rail.equals(railConnecte1) && !rail.equals(railConnecte2))
				{
					System.out.println("--- "+rail.getSemaDroite());
					System.out.println("rail.getJonctionDroite() " + rail.getJonctionDroite());
					if(rail.getSemaDroite()!=null && rail.getSemaDroite().getEtatStop()!=null && rail.getJonctionDroite().equals(this))
					{
						rail.getSemaDroite().setEtatStop();
					}
					else if(rail.getSemaGauche()!=null && rail.getSemaGauche().getEtatStop()!=null && rail.getJonctionGauche().equals(this) )
					{
						rail.getSemaGauche().setEtatStop();;
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
		
		if(!r1.equals(r2))
		{
			throw new ErreurAiguillage("Les aiguillages amont "+r1+" aval "+r2+" sont identique sur l'aiguillage "+this);
		}
		if(!rails.contains(r1) || !rails.contains(r2)){
			throw new ErreurAiguillage("un des rails passees en parametres ne sont pas dans l'aiguillage");
		}
		else{			
			setRailConnecte1(r1);
			setRailConnecte2(r2);
		}
		
		this.initialiserFeux();
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
