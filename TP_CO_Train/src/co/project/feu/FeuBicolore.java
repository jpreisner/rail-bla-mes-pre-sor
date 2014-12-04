package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class FeuBicolore extends Semaphore {

	public FeuBicolore() {
		etatActuel = new EtatFeuBicolore();
	}
	
	@Override
	public EtatFeuEnum getEtatActuel() throws ErreurSignalisation {
		return etatActuel.getEtatActuel();
	}

	@Override
	public void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation {
		etatActuel.setEtatActuel(etatFeu);
	}
}
