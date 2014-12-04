package co.project.infrastructure;

import co.project.infrastructure.rail.Rail;

public class Infrastructure {
	protected int longueur = 0; // Longueur de l'element

	public void creer10kmRail() {
		Rail r1 = FabriqueInfrastructure.creerRail(1);
		r1.setJ1(FabriqueInfrastructure.connecterUnRail(r1));

	}
}
