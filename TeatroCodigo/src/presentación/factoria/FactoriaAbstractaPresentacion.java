package presentaci�n.factoria;

import presentaci�n.IGUI;

public abstract class FactoriaAbstractaPresentacion {

	private static FactoriaAbstractaPresentacion instance;

	public static FactoriaAbstractaPresentacion getInstance() {
		if (instance == null)
			instance = new FactoriaPresentacion();
		return instance;
	}

	public abstract IGUI createVista(int id);
}