package co.project.feu;

import co.project.exception.ErreurSignalisation;

public class FeuTricolore extends Semaphore {

	private boolean feuVert;
	/* VITESSE/2 */
	private boolean feuOrange;
	/* ARRET IMMEDIAT */
	private boolean feuRouge;
	
	@Override
	public EtatFeu getEtatActuel() throws ErreurSignalisation {
		if (feuVert == feuRouge == feuRouge){
			throw new ErreurSignalisation("Feu tricolore en erreur");
		}else if(feuVert){
			return EtatFeu.VERT;
		}else if(feuOrange){
			return EtatFeu.ORANGE;
		}else {
			return EtatFeu.ROUGE;
		}
	}
}
