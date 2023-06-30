package presentacion.command.commands.actividad;

import negocio.actividad.SAActividad;
import negocio.actividad.TLineaActividad;
import negocio.factoriaNegocio.FactoriaNegocio;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class DesvincularActividad implements CommandInterface{
	SAActividad sa = FactoriaNegocio.getInstance().generarSAActividad();
    //List<TLineaGuia> tLineaGuia;
	TLineaActividad tLineaAct;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		tLineaAct =(TLineaActividad) o;
		op = sa.eliminarUsuario(tLineaAct);
		
		contexto = new Contexto(op, tLineaAct);
		return contexto;
	}
}
