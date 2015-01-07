package co.project.train;

/**
 * Classe n'intervenant pas dans l'UML
 * Permet de representer l'intervalle sur lequel se trouve le train
 *
 */
public final class Interval {
	
	private int min,max;
	
	public Interval(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public boolean contains(Interval autre)
	{
		if((autre.getMin()>=min && autre.getMin()<=max) || (autre.getMax()>=min && autre.getMax()<=max))
			return true;
		
		return false;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	

}
