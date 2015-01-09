package co.project.train;

import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import co.project.exception.ErreurCollision;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.feu.etat.coeff.stop.EtatStop;
import co.project.infrastructure.Reseau;
import co.project.infrastructure.rail.Rail;

public class Train implements Observer{
	/* compteur d'instance */
	private static int id = 0;
	private int idTrain;
	/* exprimee en nb de troncons */
	private int taille;
	/* fixe, exprimee en nombre de troncon par unite de temps */
	private int vMax;
	/* vitesse en nombre de troncons par Unite de Temps */
	private int vCourante;
	/* pattern STATE */
	/* private EtatCourant etatTrain; */
	private Rail rail;
	private Etat etat;
	
	//Timer
	AutonomeTrain execution;
	Timer time;

	/* position de la tete sur les troncons */
	public Train(int taille, int vMax, Rail rail, Direction direction) {
		this.idTrain = id;
		this.taille = taille;
		this.vMax = vMax;
		this.vCourante = vMax;
		this.etat = new Etat(direction,rail.getLongueur()-1);
		this.rail = rail;
		id++;
	}

	public int getId() {
		return idTrain;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getVitesseMax() {
		return vMax;
	}

	public void setVitesseMax(int vitesseMax) {
		this.vMax = vitesseMax;
	}

	public int getVitesseCourante() {
		return vCourante;
	}

	/**
	 * La methode change la vitesse courante en garantissant qu'on ne depasse pas la vitesse maximale
	 * @param vitesseCourante
	 */
	public void setVitesseCourante(int vitesseCourante) {
		if(vitesseCourante>vMax)
			this.vCourante = vMax;
		else
			this.vCourante = vitesseCourante;
	}

	public Rail getRail() {
		return rail;
	}

	public Etat getEtat() {
		return etat;
	}

	@Override
	public String toString() {
		return "\nTrain [ id : " + idTrain + ", taille : " + taille
				+ "\n\tRail : "+rail
				+ " \n\tEtat = "+etat
				+ "\n\tVitesse courante : " + vCourante + " tr/t"
				+ "\n\tVitesse maximale : " + vMax + " tr/t ] ";
	}

	/**
	 * Fonction du deplacement du train, il teste les collisions a chaque deplacement de train
	 * Train observe les semaphores qui sont dans sa direction
	 * @throws ErreurJonction
	 */
	public void deplacer() throws ErreurJonction , ErreurCollision {
		
		/*System.out.println("\t\t\tAVANT DEPLACEMENT -------");
		System.out.println(this);*/
		
		int deplacement = 0;
		
		if(etat.getDirection() == Direction.DROITE)
		{
			if(rail.getSemaDroite()!=null && etat.getDirection().equals(rail.getSemaDroite().getDirection()))
			{
				setVitesseCourante(rail.getSemaDroite().getEtat().getVitesse(vCourante));
				deplacement = etat.getTronconTete();
			}
		}
		else
		{
			if(rail.getSemaGauche()!=null && etat.getDirection().equals(rail.getSemaGauche().getDirection()))
			{
				setVitesseCourante(rail.getSemaGauche().getEtat().getVitesse(vCourante));
				deplacement = rail.getLongueur()-etat.getTronconTete();
			}
		}
		
		/**
		 * On se deplace jusqu'a l'etat stop
		 */
		if(vCourante==0)
		{
			for(int i = 0; i<deplacement; i++)
			{
				etat.deplaceTroncontete(1);
				try {
					Reseau.getInstance().testCollisions(this);
				} catch (ErreurCollision e) {
					break;
				}
			}
		}
		/*
		 * On se deplace seulement si on a une vitesse > 0
		 */
		else if(vCourante>0)
		{
			/**
			 * Deplacement de troncon en troncon
			 * on teste les collisions du train a chaque deplacement de tron�on
			 */
			for(int i = 0; i<vCourante; i++)
			{
				etat.deplaceTroncontete(1);
				
				if(etat.getDirection().equals(Direction.DROITE))
					algoDeplacementDroite();
				else
					algoDeplacementGauche();
				Reseau.getInstance().testCollisions(this);
			}
			
			
		}
		
		/*System.out.println("\t\t\tAPRES DEPLACEMENT -------");
		System.out.println(this);*/
	}
	
	private void algoDeplacementDroite() throws ErreurJonction {
		
		if(etat.getTronconTete() == rail.getLongueur())
		{
			rail = railSuivanteDirection();
			etat.setTronconTete(0);
		}
		
	}

	private void algoDeplacementGauche() throws ErreurJonction {
		
		if(etat.getTronconTete() == -1)
		{
			rail = railSuivanteDirection();
			etat.setTronconTete(rail.getLongueur()-1);
		}
	}

	/**
	 * Retourne la queue du train
	 * @return une PaireRailTroncon : contient l'information de la rail ou 
	 * se trouve la queue et a la fois le troncon
	 */
	public PaireRailTroncon getQueue()
	{
		/**
		 * A l'initialisation le nombre de troncon nous restant
		 * a parcourir est taille du train - la position du troncon courant
		 */
		int troncon = getTaille() - etat.getTronconTete() - 1;
		Rail precedente = null;
		boolean continuer = true;
		/**
		 * On parcours tant que notre nombre de troncon est positif 
		 * Et qu'il faut continuer a parcourir les rail
		 */
		while(troncon>=0 && continuer) {
			
			System.out.println("troncon = " + troncon);
			try {
				/**
				 * On recupere la rail precedente
				 */
				if(precedente==null)
					precedente = railPrecedenteDirection(rail);
				else
					precedente = railPrecedenteDirection(precedente);
				//System.out.println("precendente.getLongueur() = "+precedente.getLongueur());
				/**
				 * On dispose de 2 cas
				 * 1) (if) la rail n'a pas la taille suffisante pour 
				 * stocker un nombre de troncon : troncon 
				 * Auquel cas on decremente troncon de la taille de la rail
				 * 
				 * 2) (else) notre rail peut contenir un nombre de troncon : troncon
				 * Auquel cas on s'arrete
				 */
				if(etat.getDirection()==Direction.GAUCHE)
					System.out.println("Condition : "+precedente.getLongueur()+"<"+troncon);
				
				
				if(precedente.getLongueur()<troncon){
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!precedente.getLongueur()=" + precedente.getLongueur());
					troncon-=precedente.getLongueur();
				}
				else
				{
					System.out.println("!!!!!!!!!!! troncon = " + troncon);
					//troncon-=precedente.getLongueur();
					continuer = false;
				}
			} catch (ErreurJonction e) {
				return null;
			}
		}
		
		System.out.println("troncon = "+troncon + " precedente = "+precedente);
		if(precedente==null)
			return new PaireRailTroncon(rail, troncon);
		/**
		 * Pour recuperer la position exacte du troncon ou se trouve la queue
		 * On retourne la taille de la rail - le nombre de troncon nous restant
		 */
		int tmp = precedente.getLongueur()-troncon;
		//System.out.println("tmp = "+tmp);
		
		/**
		 * FAIRE UN IF DIRECTION Droite ET IF Direction Gauche
		 */
		if(troncon==0)
			return new PaireRailTroncon(precedente, troncon);
		else if(troncon>0)
			return new PaireRailTroncon(precedente, precedente.getLongueur()-troncon-1);
		else 
			return new PaireRailTroncon(precedente, precedente.getLongueur()-1);
	}
	
	/**
	 * Retourne la rail suivante de la rail courante en fonction de la direction
	 * @return la rail suivante de la rail courante en fonction de la direction
	 * @throws ErreurJonction
	 */
	public Rail railSuivanteDirection() throws ErreurJonction
	{
		if(etat.getDirection().equals(Direction.DROITE))
			return rail.getJonctionDroite().getRailSuivant(rail);
		else	
			return rail.getJonctionGauche().getRailSuivant(rail);
	}
	

	/**
	 * Retourne la rail precedente de la rail courante en fonction de la direction
	 * @return la rail suivante de la rail courante en fonction de la direction
	 * @throws ErreurJonction
	 */
	public Rail railPrecedenteDirection(Rail railParam) throws ErreurJonction
	{
		if(etat.getDirection().equals(Direction.GAUCHE))
			return railParam.getJonctionDroite().getRailSuivant(railParam);
		else	
			return railParam.getJonctionGauche().getRailSuivant(railParam);
	}
	
	/**
	 * Arrete le train
	 */
	public void stop()
	{
		setVitesseCourante(0);
		time.cancel();
		time = null;
	}
	
	public void start()
	{
		setVitesseCourante(vMax); 
		time = new Timer();
		execution = new AutonomeTrain(this);
		time.schedule(execution, 0, Reseau.intervalle);
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			EtatStop etatStop = (EtatStop) arg;
		} catch (ClassCastException e) {
			start();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this)
			return true;
		if(obj==null)
			return false;
		try {
			Train second = (Train)obj;
			return (this.idTrain == second.getId());
			
		} catch (ClassCastException e) {
			return false;
		}
	}

	public void inverseDirection() throws ErreurTrain{
		if(vCourante>0)
			throw new ErreurTrain("Impossible d'inverse la direction d'un train en mouvement");
		
		boolean exceptionButee = false;
		try {
			railSuivanteDirection();
		} catch (ErreurJonction e) {
			exceptionButee = true;
		}
		
		if(!exceptionButee)
			throw new ErreurTrain("Impossible de changer la direction de train car il n'est pas en fin de butee");
		
		
		
		PaireRailTroncon queue = getQueue();
		if(queue==null)
			throw new ErreurTrain("La queue du train "+this+" est introuvable impossible de le relancer");
		System.out.println("Paire queue "+queue);
		
		rail = queue.getRail();
		
		
		
		if(etat.getDirection().equals(Direction.DROITE))
			etat.setDirection(Direction.GAUCHE);
		else
			etat.setDirection(Direction.DROITE);
		etat.setTronconTete(queue.getTroncon());
	}
	
	class AutonomeTrain extends TimerTask{

		Train t;
		
		protected AutonomeTrain(Train t) {
			this.t = t;
		}
		
		@Override
		public void run() {
			try {
				t.deplacer();
				System.out.println("prout : "+t);
			} catch (ErreurCollision e) {
				e.printStackTrace();
			} catch (ErreurJonction e) {
				t.stop();
				try 
				{
					t.inverseDirection();
				} 
				catch (ErreurTrain e1) 
				{
					e1.printStackTrace();
				}
				t.start();
				System.out.println("\t-\tMESSAGE SYSTEME : Le train " +t+ " est arrive en fin de butee");
			}
			
			
			
		}
		
	}

	public Timer getTime() {
		return time;
	}
	
	
}
