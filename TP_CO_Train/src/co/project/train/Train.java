package co.project.train;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import co.project.exception.ErreurCollision;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.feu.etat.coeff.stop.EtatStop;
import co.project.infrastructure.Reseau;
import co.project.infrastructure.rail.Rail;

public class Train implements Observer{
	
	private enum Retour{
		EN_BUTEE,
		NON_EN_BUTEE;
	}
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
	private boolean enDeplacement = false;
	
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
	public Retour deplacer() throws ErreurJonction , ErreurCollision {
		
		enDeplacement = true;
		/*System.out.println("\t\t\tAVANT DEPLACEMENT -------");
		System.out.println(this);*/
		
		int deplacement = 0;
		
		if(etat.getDirection() == Direction.DROITE)
		{
			//System.out.println("..........................................."+rail.getSemaDroite());
			if(rail.getSemaDroite()!=null && etat.getDirection().equals(rail.getSemaDroite().getDirection()))
			{
				setVitesseCourante(rail.getSemaDroite().getEtat().getVitesse(vCourante));
				deplacement = rail.getLongueur() - 1 - etat.getTronconTete();
			}
		}
		else
		{
			if(rail.getSemaGauche()!=null && etat.getDirection().equals(rail.getSemaGauche().getDirection()))
			{
				setVitesseCourante(rail.getSemaGauche().getEtat().getVitesse(vCourante));
				deplacement = etat.getTronconTete();
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
				{
					if(algoDeplacementDroite()==Retour.EN_BUTEE)
						return Retour.EN_BUTEE;
				}
				else
				{
					if(algoDeplacementGauche()==Retour.EN_BUTEE)
						return Retour.EN_BUTEE;
				}
				Reseau.getInstance().testCollisions(this);
			}
			
			
		}
		
		enDeplacement = false;
		
		return Retour.NON_EN_BUTEE;
		
		/*System.out.println("\t\t\tAPRES DEPLACEMENT -------");
		System.out.println(this);*/
	}
	
	private Retour algoDeplacementDroite(){
		
		//System.out.println("geTranconTete Avant : "+etat.getTronconTete() + " true ou false "+(etat.getTronconTete() == rail.getLongueur()));
		if(etat.getTronconTete() == rail.getLongueur())
		{
			Rail tmp = railSuivanteDirection();
			if (tmp != null) {
				rail.retirerTrain(this);
				rail = railSuivanteDirection();
				rail.ajouterTrain(this);
				etat.setTronconTete(0);
				return Retour.NON_EN_BUTEE;
			} else {
				etat.setTronconTete(rail.getLongueur() - 1);
				return Retour.EN_BUTEE;
			}
		}
		return Retour.NON_EN_BUTEE;
		//System.out.println("geTranconTete Apres: "+etat.getTronconTete());
	}

	private Retour algoDeplacementGauche() {
		
		if (etat.getTronconTete() == -1) {
			Rail tmp = railSuivanteDirection();
			if (tmp != null) {
				rail.retirerTrain(this);
				rail = railSuivanteDirection();
				rail.ajouterTrain(this);
				etat.setTronconTete(rail.getLongueur() - 1);
				return Retour.NON_EN_BUTEE;
			} else {
				etat.setTronconTete(0);
				return Retour.EN_BUTEE;
				
			}

		}
		
		return Retour.NON_EN_BUTEE;
	}

	/**
	 * Retourne la queue du train
	 * @return une PaireRailTroncon : contient l'information de la rail ou 
	 * se trouve la queue et a la fois le troncon
	 * @throws ErreurJonction 
	 */
	public PaireRailTroncon getQueue()
	{
		
		
		//System.out.println("\t\t\t\t\t TRAIN AVANT " + this);
		
		
		Rail precedente = null;
		/**
		 * A l'initialisation le nombre de troncon nous restant
		 * a parcourir est taille du train - la position du troncon courant
		 */
		//int nbTroncon = getTaille() - etat.getTronconTete() - 1;
		int nbTroncon = getTaille();
		//System.out.println("--------------- nbTrancon initial "+nbTroncon);
		//Dir G
		
		if(etat.getDirection().equals(Direction.DROITE))
		{
			if(etat.getTronconTete()<nbTroncon)
			{
				//System.out.println("getTrancon tete "+etat.getTronconTete());
				nbTroncon -= (etat.getTronconTete()+1);
				//System.out.println("----------------- nbTroncon : "+nbTroncon);
				precedente = railPrecedenteDirection(rail);
			}
			else
			{
				return new PaireRailTroncon(rail, etat.getTronconTete()-nbTroncon);
			}
		}
		//Dir D
		else
		{
			if(rail.getLongueur()-etat.getTronconTete()<nbTroncon)
			{
				//System.out.println("Direction " + etat.getDirection() + " rail actuel" + rail +  "rail precedente" + railPrecedenteDirection(rail));
				precedente = railPrecedenteDirection(rail);
				nbTroncon -= (precedente.getLongueur()-etat.getTronconTete());
				//System.out.println("----------------- nbTroncon : "+nbTroncon);
			}
			else
			{
				return new PaireRailTroncon(rail, etat.getTronconTete()+nbTroncon);
			}
		}
		
		
		
		
		boolean continuer = true;
		/**
		 * On parcours tant que notre nombre de troncon est positif 
		 * Et qu'il faut continuer a parcourir les rail
		 */
		while(nbTroncon>=0 && continuer) {
				/**
				 * On recupere la rail precedente
				 */

					
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
				//D->G
				if(etat.getDirection()==Direction.DROITE)
				{
					if(nbTroncon>=precedente.getLongueur())
					{
						precedente = railPrecedenteDirection(precedente);
						nbTroncon -= precedente.getLongueur();
					}
					else
					{
						return new PaireRailTroncon(precedente, precedente.getLongueur()-nbTroncon-1);
					}
				}
				else
				{
					if(nbTroncon>=precedente.getLongueur())
					{
						precedente = railPrecedenteDirection(precedente);
						nbTroncon -= precedente.getLongueur();
					}
					else
					{
						return new PaireRailTroncon(precedente, nbTroncon);
					}
					
				}
			
		}
		
		return null;
	}
	
	/**
	 * Retourne la rail suivante de la rail courante en fonction de la direction
	 * @return la rail suivante de la rail courante en fonction de la direction
	 * @throws ErreurJonction
	 */
	public Rail railSuivanteDirection()
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
	public Rail railPrecedenteDirection(Rail railParam)
	{
		
		if(etat.getDirection().equals(Direction.GAUCHE))
		{
			return railParam.getJonctionDroite().getRailSuivant(railParam);
		}
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
	}
	
	public void start()
	{
		enDeplacement = false;
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
			if(railSuivanteDirection()==null)
			{
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
			
			if(!t.enDeplacement)
			{
				try {
					if(t.deplacer()==Retour.EN_BUTEE)
					{
						t.stop();
						try {
							t.inverseDirection();
						} catch (ErreurTrain e) {
							e.printStackTrace();
						}
						t.start();
					}
				} catch (ErreurCollision e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ErreurJonction e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Train <====> : "+t+"\n\n");
			}
		}
		
	}

	public Timer getTime() {
		return time;
	}
	
	
}
