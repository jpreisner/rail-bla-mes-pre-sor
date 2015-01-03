package co.project.feu;

public abstract class EtatFeu  {
	
	protected String name;
	
	public EtatFeu(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public abstract EtatFeu changeEtat(Semaphore sema);

	public String getName() {
		return name;
	}
	
	
}
