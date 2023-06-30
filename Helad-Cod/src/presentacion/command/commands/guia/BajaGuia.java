package presentacion.command.commands.guia;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.guia.SAGuia;
import negocio.guia.TGuia;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class BajaGuia implements CommandInterface{

	SAGuia saguia = FactoriaNegocio.getInstance().generarSAGuia();

	@Override
	public Contexto execute(Object o) {
		Integer id = (Integer) o;
		int op = saguia.bajaGuia(id);

		Contexto contexto = new Contexto(op, id);

		return contexto;
	}
}
