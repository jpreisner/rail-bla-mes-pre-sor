package co.project.feu;

import java.util.Arrays;

import co.project.exception.ErreurSignalisation;

public class FeuTricolore extends Semaphore {

	public FeuTricolore() {
		etatsPossibles = new EtatFeuEnum[]{EtatFeuEnum.VERT, EtatFeuEnum.ORANGE, EtatFeuEnum.ROUGE};
		etatActuel = etatsPossibles[0];
	}

	@Override
	public void setEtatActuel(EtatFeuEnum etatFeu) throws ErreurSignalisation {
		if(!Arrays.asList(etatsPossibles).contains(etatFeu)){
			throw new ErreurSignalisation("signalisation impossible pour ce feu Tricolore");
		}else{
			this.etatActuel = etatFeu;
		}
	}
	
	@Override
	public String toString() {
		return "Feu Tricolore, "+etatActuel;
	}
}
