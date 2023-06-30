package negocio.actividad;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;

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

public class Read {
	private static TActCultural tActCult = new TActCultural(-1, "T_READ", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("19:30:00"), 2, 15.0, true);
	private static TRepresentacion tRepresentacion = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("18:30:00"), 2, -1, -1, -1, true);
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "T_READ", "Mike Wazowski", 2021, true);
	private static TCompañia tComp = new TCompañia(-1, "T_READ", "Comedia", true);
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
		
		tActCult.setId(saActividad.create(tActCult));
		
		tRepresentacion.setIdObra(tObra.getId());
		tRepresentacion.setIdCompañia(tComp.getId());
		tRepresentacion.setIdTemporada(tTemporada.getId());
		tRepresentacion.setId(saActividad.create(tRepresentacion));
	}
	
	@Test
	public void buscarActCultCorrecto(){
		TActividad res = saActividad.read(tActCult.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarRepresentacionCorrecto(){
		TActividad res = saActividad.read(tRepresentacion.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarActCultNoActivo(){
		saActividad.delete(tActCult.getId());
		TActividad res = saActividad.read(tActCult.getId());
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarRepresentacionNoActivo(){		
		saActividad.delete(tRepresentacion.getId());
		TActividad res = saActividad.read(tRepresentacion.getId());
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarIdIncorrecto(){
		TActividad res = saActividad.read(-1);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saActividad.deleteFisico(tActCult.getId());
		
		saActividad.deleteFisico(tRepresentacion.getId());
		
		saObra.deleteFisico(tObra.getId());
		saCompañia.deleteFisico(tComp.getId());
		saTemporada.deleteFisico(tTemporada.getId());
	}
}
