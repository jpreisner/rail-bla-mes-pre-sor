package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		etatActuel = new EtatFeuTricolore();
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
