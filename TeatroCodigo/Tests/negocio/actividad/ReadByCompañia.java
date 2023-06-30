package negocio.actividad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;

public class ReadByCompañia {

	private static TRepresentacion tRepresentacion = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("18:30:00"), 2, -1, -1, -1, true);
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "T_READ_BY_COMPAÑIA", "Mike Wazowski", 2021, true);
	private static TCompañia tComp = new TCompañia(-1, "T_READ_BY_COMPAÑIA", "Comedia", true);
	private static TTemporada tTemporada = new TTemporada(-1, 37, 4, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), true);
	
	private static SAActividad saActividad;
	private static SAObra saObra;
	private static SACompañia saCompañia;
	private static SATemporada saTemporada;
	
	@BeforeClass
	public static void inicio(){
		saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		saCompañia = FactoriaAbstractaNegocio.getInstance().createSACompañia();
		saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		
		tObra.setId(saObra.create(tObra));
		tComp.setId(saCompañia.create(tComp));
		tTemporada.setId(saTemporada.create(tTemporada));
		
		tRepresentacion.setIdObra(tObra.getId());
		tRepresentacion.setIdCompañia(tComp.getId());
		tRepresentacion.setIdTemporada(tTemporada.getId());
		tRepresentacion.setId(saActividad.create(tRepresentacion));
	}
	
	@Test
	public void buscarRepresentacionCorrecto(){
		Collection<TRepresentacion> res = saActividad.readByCompañia(tComp.getId());
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarRepresentacionIncorrecto(){
		Collection<TRepresentacion> res = saActividad.readByCompañia(-1);
		try{
			assertTrue(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		
		saActividad.deleteFisico(tRepresentacion.getId());
		
		saObra.deleteFisico(tObra.getId());
		saCompañia.deleteFisico(tComp.getId());
		saTemporada.deleteFisico(tTemporada.getId());
	}

}
