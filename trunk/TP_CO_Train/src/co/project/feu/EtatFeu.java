package co.project.feu;

public abstract class EtatFeu  {
	
	protected String name;
	
	public EtatFeu(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	/**
	 * @param sema
	 * @return l'état suivant dans du cycle
	 * pour feu Bicolore : VERT -> ROUGE -> VERT
	 * pour le feu Tricolore : VERT -> ORANGE -> ROUGE -> VERT
	 */
	public abstract EtatFeu changeEtat(Semaphore sema);

	public String getName() {
		return name;
	}
	
	
}
