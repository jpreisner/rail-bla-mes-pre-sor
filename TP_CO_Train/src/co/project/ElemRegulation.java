package co.project;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import co.project.capteur.Capteur;
import co.project.exception.ErreurAiguillage;
import co.project.exception.ErreurSemaphore;
import co.project.feu.semaphore.Semaphore;
import co.project.infrastructure.jonction.Aiguillage;
import co.project.infrastructure.rail.Rail;
import co.project.train.Train;

public class ElemRegulation implements Observer {

	private ArrayList<Aiguillage> listAiguillage;
	private ArrayList<Capteur> listCapteur;
	private ArrayList<Semaphore> listSemaphores;

	/**
	 * Element de regulation d'un aiguillage
	 * @param aiguillage
	 */
	public ElemRegulation(Aiguillage aiguillage) {
		this.listAiguillage =new ArrayList<Aiguillage>() ;
		this.listCapteur = new ArrayList<Capteur>();
		this.listSemaphores = new ArrayList<Semaphore>();
	}
	
	/**
	 * Element de regulation d'une ligne droite
	 */
	public ElemRegulation() {
		this.listCapteur = new ArrayList<Capteur>();
		this.listSemaphores = new ArrayList<Semaphore>();
	}

	public ArrayList<Capteur> getListCapteurs() {
		return listCapteur;
	}	
	
	public ArrayList<Semaphore> getListSemaphores() {
		return listSemaphores;
	}


	public ArrayList<Aiguillage> getListAiguillage() {
		return listAiguillage;
	}

	public void ajoutObserver(){
		for(Capteur capt : listCapteur){
			capt.addObserver(this);
		}
	}
	
	public void ajoutListCapteur(ArrayList<Capteur> cap)
	{
		listCapteur.addAll(cap);
		ajoutObserver();
	}
	
	public void ajoutListSemaphores(ArrayList<Semaphore> sem)
	{
		listSemaphores.addAll(sem);
		ajoutObserver();
	}
	
	/*
	 * action en fonction des informations du capteur 2 cas possibles :
	 * favoritisme systematique d'une des branches du reseau OU systeme FIFO.
	 */
	public void gestionAiguillage() {

	}
	
	public void gestionAiguillageTrain(Train t)
	{
		
	}
	
	/**
	 * Mettre toutes les semaphores dans ETATSTOP avant changement
	 * @param aiguillage 
	 */
	private void bloquerSemaphores(Aiguillage aiguillage){
		for (Rail rail : aiguillage.getRails()) {
			try {
				if(rail.getJonctionDroite().equals(aiguillage)){
					if(rail.getSemaDroite()!=null)
					{
						rail.getSemaDroite().setEtatStop();
					}
				}
				else if(rail.getJonctionGauche().equals(aiguillage)){
					if(rail.getSemaGauche()!=null)
					{
						rail.getSemaGauche().setEtatStop();
					}
				}
			} catch (ErreurSemaphore e) {
				System.out.println("Passage de tous les semaphores a un etat d'arret impossible");
			}
		}
	}
	
	/**
	 * Effectue le changement d'aiguillage selon les rails en parametres
	 * @param aiguillage
	 * @param r1
	 * @param r2
	 * @param sensPassage : true si sens semaphore  r1->r2, false si sens semaphore r2->r1
	 * @throws ErreurAiguillage
	 */
	public void changementAiguillage(Aiguillage aiguillage, Rail r1, Rail r2) throws ErreurAiguillage{
		if(!listAiguillage.contains(aiguillage)){
			throw new ErreurAiguillage("L'aiguillage n'appartient pas a cet element de regulation");
		}
		/* passe les semaphores au rouge */
		bloquerSemaphores(aiguillage);
		
		/* change l'aiguillage */
		aiguillage.changementAiguillage(r1, r2);
		
		/* repasse les deux semaphores au vert */
		aiguillage.initialiserFeux();
	}

	/**
	 * 
	 * @param Observable
	 *            o : observable ayant envoye une notification
	 * @param Object
	 *            arg : objet notifie (peut etre nulle)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		try {
			Capteur capt = (Capteur)arg;
			
			ArrayList<Rail> railDispo = new ArrayList<Rail>();
			
			if(capt.getNumTronconRail() > capt.getRail().getLongueur()/2)
			{
				try {
					Aiguillage a = (Aiguillage)capt.getRail().getJonctionDroite();
					
					if(!capt.getRail().getSemaDroite().isEtatNeutre() && !a.trainPassant())
					{
						for(Rail rail : a.getRails())
						{
							if(rail.getTrains().isEmpty())
							{
								railDispo.add(rail);
							}
						}
						
						if(railDispo.isEmpty())
						{
							throw new ErreurAiguillage("Tout les trains attendent a l'aiguillage");
						}
						else
						{
							int random = (int) (Math.random()*(railDispo.size()));
							Rail r2 = railDispo.get(random);
							
							try {
								a.changementAiguillage(capt.getRail(), r2);
							} catch (ErreurAiguillage e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}

					
				} catch (ClassCastException e) {
					// TODO: handle exception
				} catch (ErreurAiguillage e) {
					System.out.println("Tout les trains attendent a l'aiguillage");
				}
			}
			else
			{
				try {
					Aiguillage a = (Aiguillage)capt.getRail().getJonctionGauche();
					
					if(!capt.getRail().getSemaGauche().isEtatNeutre() && !a.trainPassant())
					{
						for(Rail rail : a.getRails())
						{
							if(rail.getTrains().isEmpty())
							{
								railDispo.add(rail);
							}
						}
						
						if(railDispo.isEmpty())
						{
							throw new ErreurAiguillage("Tout les trains attendent a l'aiguillage");
						}
						else
						{
							int random = (int) (Math.random()*(railDispo.size()));
							Rail r2 = railDispo.get(random);
							
							try {
								a.changementAiguillage(capt.getRail(), r2);
							} catch (ErreurAiguillage e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}

					
				} catch (ClassCastException e) {
					// TODO: handle exception
				} catch (ErreurAiguillage e) {
					System.out.println("Tout les trains attendent a l'aiguillage");
				}
			}
			
		} catch (ClassCastException e) {
			// TODO: handle exception
		}

	}
}
