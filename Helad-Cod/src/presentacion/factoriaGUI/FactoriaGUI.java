package presentacion.factoriaGUI;

import presentacion.controlador.IGUI;

public abstract class FactoriaGUI{
	private static FactoriaGUI instance;
	
    public static synchronized FactoriaGUI getInstance() {
        if (instance == null)
            instance = new FactoriaGUIImp();
        return instance;
    }

    public abstract IGUI createVista(int id);
}