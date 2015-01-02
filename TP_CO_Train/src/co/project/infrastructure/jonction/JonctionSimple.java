package co.project.infrastructure.jonction;

import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.infrastructure.rail.Rail;

public class JonctionSimple extends Jonction {

	private Rail rail1;
	private Rail rail2;

	public JonctionSimple(Rail rail1, Rail rail2) throws ErreurConstruction {
		super(0);
		this.rail1 = rail1;
		this.rail2 = rail2;
		connecteRailJonction();
	}

	@Override
	public Rail getRailSuivant(Rail rail) throws ErreurJonction {
		if (rail.equals(this.rail1)) {
			return this.rail2;
		} else if (rail.equals(this.rail2)) {
			return this.rail1;
		} else {
			throw new ErreurJonction("Erreur dans la recuperation du rail suivant");
		}
	}

	@Override
	public boolean trainPasse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "[JS]";
	}

	@Override
	public void connecteRailJonction() throws ErreurConstruction {
		if (!rail1.connectable() || !rail2.connectable()) {
			throw new ErreurConstruction("Les 2 rails ont deja des jonctions, pose de jonction simple impossible");
		} else {
			if(rail1.getJonctionDroite()==null){
				rail1.setJonctionDroite(this);
			}else{
				rail1.setJonctionGauche(this);
			}
			
			if(rail2.getJonctionGauche()==null){
				rail2.setJonctionGauche(this);
			}else{
				rail2.setJonctionDroite(this);
			}
		}
	}
}
