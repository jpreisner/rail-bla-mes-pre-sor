package co.project.train;

import co.project.infrastructure.rail.Rail;

public class PaireRailTroncon extends Paire<Rail,Integer>{
	
	public PaireRailTroncon(Rail rail, Integer troncon) {
		super(rail, troncon);
	}

	public Rail getRail()
	{
		return getFirst();
	}
	
	public Integer getTroncon()
	{
		return getSecond();
	}
}
