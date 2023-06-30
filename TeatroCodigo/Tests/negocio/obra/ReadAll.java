package negocio.obra;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import integración.factoria.FactoriaIntegracion;

import java.util.Collection;
import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import static org.junit.Assert.assertTrue;

public class ReadAll {
	private static final int anio = 2020;
	private static final String sinopsis = "TEST_OBRA_READ_ALL";
	private static final String genero = "TEST_OBRA_READ_ALL";
	private static final String autor = "TEST_OBRA_READ_ALL";
	private static final String titulo = "TEST_OBRA_READ_ALL";
	public static int idObra, idCorr, idIncorr, idNoEncontrado;
	private static TObra o;
	private static SAObra sa;
	
	@BeforeClass
	public static void init() {
		sa = FactoriaNegocio.getInstance().createSAObra();
		o = new TObra(0, genero, sinopsis, titulo, autor, anio,true);
		idObra = sa.create(o);
		if (idObra == -1){
			TObra aux = FactoriaIntegracion.getInstance().createDAOObra().readByName(titulo);
			idObra = aux.getId();
		}
	}
	
	@Test
	public void TestDatosCorrecto() {
		SAObra sa = FactoriaNegocio.getInstance().createSAObra();
		Collection<TObra> col;
		col = sa.readAll();
		
		assertTrue(col != null);
	}
	
	@AfterClass
	public static void destroyClass() {
		sa.deleteFisico(idObra);
	}
	
}
