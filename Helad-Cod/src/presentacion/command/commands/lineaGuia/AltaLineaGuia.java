package presentacion.command.commands.lineaGuia;


import negocio.factoriaNegocio.FactoriaNegocio;
import negocio.guia.SAGuia;
import negocio.guia.TLineaGuia;
import presentacion.command.CommandInterface;
import presentacion.command.Contexto;

public class AltaLineaGuia implements CommandInterface {
	SAGuia sa = FactoriaNegocio.getInstance().generarSAGuia();
    //List<TLineaGuia> tLineaGuia;
	TLineaGuia tLineaGuia;
    int op = -1;
    Contexto contexto;
	@Override
	public Contexto execute(Object o) {
		tLineaGuia =(TLineaGuia) o;
		op = sa.anyadirExposicion(tLineaGuia);
		
		contexto = new Contexto(op, tLineaGuia);
		return contexto;
	}
}
