package co.project.infrastructure.jonction;

import co.project.infrastructure.rail.Rail;

public class JonctionSimple extends Jonction {

	private Rail rail1;
	private Rail rail2;

	public JonctionSimple(Rail rail1, Rail rail2) {
		this.rail1 = rail1;
		this.rail2 = rail2;
	}
}
