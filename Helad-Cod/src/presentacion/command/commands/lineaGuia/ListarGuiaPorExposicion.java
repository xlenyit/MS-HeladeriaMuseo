package presentacion.command.commands.lineaGuia;

import presentacion.command.CommandInterface;

import java.util.ArrayList;
import java.util.List;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.guia.LineaGuia;
import negocio.guia.SAGuia;
import negocio.guia.TGuia;
import presentacion.command.Contexto;
import presentacion.controlador.Evento;

public class ListarGuiaPorExposicion implements CommandInterface{
	SAGuia saguia = FactoriaNegocio.getInstance().generarSAGuia();
	List<TGuia> lista = new ArrayList<TGuia>();
	List<LineaGuia> lista2 = new ArrayList<LineaGuia>();

	@Override
	public Contexto execute(Object o) {
		Integer id = (Integer) o;
		lista = saguia.listarPorExposicion(id);
		return new Contexto(Evento.LISTAR_GUIA_POR_EXPOSICION, lista);
	}

}
