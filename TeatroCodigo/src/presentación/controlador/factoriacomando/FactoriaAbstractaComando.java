package presentación.controlador.factoriacomando;

import presentación.controlador.comando.Comando;

public abstract class FactoriaAbstractaComando {
	private static FactoriaAbstractaComando instance;

	public static FactoriaAbstractaComando getInstance() {
		if (instance == null)
			instance = new FactoriaComando();
		return instance;
	}
	public abstract Comando createComando(int evento);

}
