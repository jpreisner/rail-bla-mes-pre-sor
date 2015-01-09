package co.project.infrastructure;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import co.project.ElemRegulation;
import co.project.capteur.Capteur;
import co.project.exception.ErreurCollision;
import co.project.exception.ErreurConstruction;
import co.project.exception.ErreurJonction;
import co.project.exception.ErreurTrain;
import co.project.infrastructure.rail.Rail;
import co.project.train.Direction;
import co.project.train.PaireRailTroncon;
import co.project.train.Train;

public final class Reseau {

	/* Unique instance non initialisee */
	private static Reseau INSTANCE = null;
	private ArrayList<ElemRegulation> regulations;
	private ArrayList<Infrastructure> reseauInfra;
	private ArrayList<Train> matRoulant;
	private Timer timer;
	private ExecutionReseau execution;
	public static int intervalle = 1000;

	/**
	 * Constructeur prive
	 */
	private Reseau() {
		this.regulations = new ArrayList<ElemRegulation>();
		this.reseauInfra = new ArrayList<Infrastructure>();
		this.matRoulant = new ArrayList<Train>();
	}

	/**
	 * @return unique point d'acces du singleton reseau
	 */
	public static synchronized Reseau getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Reseau();
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getInstance();
	}
	
	public ArrayList<ElemRegulation> getRegulations(){
		return regulations;
	}
	
	public ArrayList<Infrastructure> getReseauInfra() {
		return reseauInfra;
	}

	public ArrayList<Train> getMatRoulant() {
		return matRoulant;
	}

	public void addElemRegulation(ElemRegulation e) {
		regulations.add(e);
	}
	
	public void addTrain(Train t) {
		matRoulant.add(t);
	}

	public void addPartieReseau(ArrayList<Infrastructure> alInfra) {
		reseauInfra.addAll(alInfra);
	}
	
	public void start(){
		
		System.out.println("Etat du reseau avant demarrage" + Reseau.getInstance());
		
		timer = new Timer();
		execution = new ExecutionReseau();
		//2 eme parametre en ms
		timer.schedule(execution, 0, intervalle);
		
	}
	
	public void stop(){
		
		timer.cancel();
		
	}
	
	
	/**
	 * Mettre les trains au bon endroit sur les rails
	 */
	public void init(){
		
	}
	
	/**
	 * Fonction permettant le déplacement d'un train dans le réseau
	 * @param train
	 * @throws ErreurJonction
	 * @throws ErreurCollision
	 */
	public void deplacementTrain(Train train) throws ErreurJonction,ErreurCollision
	{
		train.deplacer();
	}
	
	
	/**
	 * S'il y a une erreur de construction du reseau
	 * @throws ErreurConstruction
	 */
	
	/**
	 * A COMPLETER
	 */
	public void verifieReseau() throws ErreurConstruction
	{
		
		if(reseauInfra.size()==0)
			throw new ErreurConstruction("Votre reseau est vide");
		else
		{
			/* si un rail a une jonction manquante */
			for(Infrastructure infra : reseauInfra){
				if(!infra.verifierElement())
					throw new ErreurConstruction("Element "+infra+" est mal construit");
			}
			
			/* si un  reseau a un element null*/
			for(Infrastructure infra : reseauInfra){
				if(infra == null){					
					throw new ErreurConstruction("Un element du reseau est null");
				}
			}
			
			for (Train train : matRoulant) {
				if ((train.getQueue() == null) || train.getRail() == null){
					throw new ErreurConstruction("Train : "+train+" n'est pas correctement sur le reseau");
				}
			}

		}
	}
	
	/**
	 * Deplace tous les trains du reseau ( troncon par troncon )
	 */
	public void deplaceTousTrains(){
		for (Train train : matRoulant) {
			try {
				deplacementTrain(train);
			} catch (ErreurJonction | ErreurCollision e) {
				
			}
		}
	}
	
	/**
	 * Pour tester si dans le reseau, il y a une collision entre les differents trains et celui en parametre
	 * @param train 
	 * @throws ErreurCollision
	 * @throws ErreurJonction 
	 */
	public void testCollisions(Train train) throws ErreurCollision, ErreurJonction{
		/* collision de face*/
		testCollisionFace(train);
				
		/* collision en tete a queue*/	
		testCollisionQueue(train);
	}
	
	/**
	 * 
	 * Cas ou la tete de "train" touche la queue d'un autre train2!=train
	 * @param train
	 * @throws ErreurCollision
	 * @throws ErreurJonction
	 */
	private void testCollisionQueue(Train train) throws ErreurCollision, ErreurJonction {
		
		for (Train train2 : matRoulant) { 
			PaireRailTroncon queue = train2.getQueue();
			if(!train.equals(train2)){
				if(train.getRail().equals(queue.getRail())){
					/* 1) Tete et queue son sur meme rail
					 * 	a) Queue et tete ne sont pas separe par un troncon
					 * 	b) Ils sont separe par un troncon*/
					//Train derriere Train2
					if(train.getEtat().getTronconTete() < queue.getTroncon())
					{
						if(train.getVitesseCourante()>train2.getVitesseCourante())
							throw new ErreurCollision("Collision : le train : "+train+
									"\n a rattrape le train : "+train2);
					}
					else if(train.getQueue().getTroncon() < train2.getEtat().getTronconTete())
					{
						if(train.getVitesseCourante()<train2.getVitesseCourante())
							throw new ErreurCollision("Collision : le train : "+train2+
									"\n a rattrape le train : "+train);
					}
					
					
					/*if(Math.abs(train.getEtat().getTronconTete()-queue.getTroncon())<=2){
						throw new ErreurCollision("Collision : le train : "+train+
							"\n a rattrape le train : "+train2);
					}*/
				}else{
					/* cas 2 : rail differente */
					if(train.railSuivanteDirection().equals(queue.getRail())){
						//train est colle a l'extremite de sa rail et train2 a l'extremite aussi (en troncon 0 par contre)
						if(train.getRail().getLongueur()-1 == train.getEtat().getTronconTete() &&
								queue.getTroncon()==0){
							/* pas de troncon qui separe les 2 trains */
							if(train.getVitesseCourante()>train2.getVitesseCourante())
								throw new ErreurCollision("Collision : le train : "+train+
										"\n a rattrape le train : "+train2);
						}
						//train separe d'une rail de queue du train2
						else if(isTrainSepare(train, queue))
						{
							if(train.getVitesseCourante()>train2.getVitesseCourante())
								throw new ErreurCollision("Collision : le train : "+train+
										"\n a rattrape le train : "+train2);
						}
					}
				}
			}
		}	
	}
	
	/**
	 * @param train
	 * @param train2
	 * @return true/false si le train et la queue de train sont separes d'1 troncon sur des rails differentes 
	 */
	private boolean isTrainSepare(Train train, PaireRailTroncon paire)
	{
		return (train.getRail().getLongueur()-2 == train.getEtat().getTronconTete() && 
				paire.getTroncon() == 0)||
				(train.getRail().getLongueur()-1 == train.getEtat().getTronconTete() && 
				paire.getTroncon() == 1);
	}
	
	/**
	 * 
	 * Cas ou la tete du train touche la tete d'un autre train en parametre
	 * @param train
	 * @throws ErreurCollision
	 * @throws ErreurJonction
	 */
	private void testCollisionFace(Train train) throws ErreurCollision, ErreurJonction {

		for (Train train2 : matRoulant) { 
			if(!train.equals(train2)){
				if(train.getRail().equals(train2.getRail())){
					/* cas 1 : meme rail, un troncon separe les 
					 * 2 trains ou pas de troncon separe les 2 trains*/
					if(Math.abs(train.getEtat().getTronconTete()-train2.getEtat().getTronconTete())<=2){
						throw new ErreurCollision("Collision de face entre le train : "+train+
							"\n et le train : "+train2);
					}
				}else{
					/* cas 2 : rail differente */
					if(train.railSuivanteDirection().equals(train2.getRail())){
						if(train.getRail().getLongueur()-1 == train.getEtat().getTronconTete() &&
								train2.getEtat().getTronconTete()==0){
							/* pas de troncon qui separe les 2 trains */
							throw new ErreurCollision("Collision de face entre le train : "+train+
									"\n et le train : "+train2);
						}else if(isTrainSepare(train, train2) || isTrainSepare(train2, train))
						{
							throw new ErreurCollision("Collision de face entre le train : "+train+
									"\n et le train : "+train2);
						}
					}
				}
			}
		}		
	}
	
	/**
	 * @param train
	 * @param train2
	 * @return true/false si les trains sont separes d'1 troncon sur des rails differentes 
	 */
	private boolean isTrainSepare(Train train, Train train2)
	{
		return (train.getRail().getLongueur()-2 == train.getEtat().getTronconTete() && 
				train2.getEtat().getTronconTete()==0);
	}

	@Override
	public String toString() {
		String result = "";
		
		for(Infrastructure infra : reseauInfra){
			result += infra ;
		}
		
		for(Train train : matRoulant){
			result += train ;
		}
		
		return result;
	}
	
	class ExecutionReseau extends TimerTask
	{

		@Override
		public void run() {
			
			System.out.println("Unite de temps : \n");
			for(Infrastructure r : Reseau.getInstance().getReseauInfra())
			{
				try {
					
					Rail rail = (Rail)r;
					for(Entry<Capteur,Integer> entry : rail.getCapteurTroncon().entrySet())
					{
						Capteur key = entry.getKey();
						key.notifieTrainPassant();
					}
					
				} catch (ClassCastException e) {
					
				}
			}
			
			for(Train train : Reseau.getInstance().getMatRoulant())
			{

					if(train.getTime()==null)
					{
						train.start();
					}
			
			}
			
			System.out.println(Reseau.getInstance()+"\n-----------\n\n");
		}
		
	}

}
