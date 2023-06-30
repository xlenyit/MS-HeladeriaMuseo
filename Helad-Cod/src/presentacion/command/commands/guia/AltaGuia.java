package presentacion.command.commands.guia;

import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.guia.SAGuia;
import negocio.guia.TGuia;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaGuia implements CommandInterface {

	SAGuia saguia = FactoriaNegocio.getInstance().generarSAGuia();

	@Override
	public Contexto execute(Object o) {
		TGuia tGuia = (TGuia) o;
		int op = saguia.altaGuia(tGuia);

		Contexto contexto = new Contexto(op, tGuia);

		return contexto;
	}

}
