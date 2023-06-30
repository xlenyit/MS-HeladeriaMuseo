package presentacion.command.commands.guia;

import java.util.ArrayList;
import java.util.List;
import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.guia.LineaGuia;
import negocio.guia.SAGuia;
import negocio.guia.TGuia;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarGuia implements CommandInterface {

	SAGuia saguia = FactoriaNegocio.getInstance().generarSAGuia();
	List<TGuia> lista = new ArrayList<TGuia>();
	List<LineaGuia> lista2 = new ArrayList<LineaGuia>();

	@Override
	public Contexto execute(Object o) {
		Integer id = (Integer) o;
//		if (id != 0) {
//			lista2 = saguia.listarPorExposicion(id);
//			return new Contexto(id, lista2);
//		} else {
//			lista = saguia.listarGuia();
//			return new Contexto(id, lista);
//		}
		
		lista = saguia.listarGuia();
		return new Contexto(Evento.LISTAR_GUIA, lista);
	}

}