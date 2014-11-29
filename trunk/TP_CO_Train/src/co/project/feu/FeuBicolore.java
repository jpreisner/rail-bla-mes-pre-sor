package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class FeuBicolore extends Semaphore {

	protected boolean feuVert;
	/* ARRET IMMEDIAT */
	protected boolean feuRouge;
	
	@Override
	public EtatFeu getEtatActuel() throws ErreurSignalisation {
		if(feuVert == feuRouge){
			throw new ErreurSignalisation("Feu bicolore en erreur");
		}else if(feuVert){
			return EtatFeu.VERT;
		}else {
			return EtatFeu.ROUGE;
		}
	}
}
