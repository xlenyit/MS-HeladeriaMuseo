package negocio.actividad;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;

import org.junit.After;
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

public class Update {
	private static TActCultural tActCult = new TActCultural(-1, "T_UPDATE", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("19:30:00"), 2, 15.0, true);
	private static TActCultural tActCultMod = new TActCultural(-1, "T_UPDATE_MOD", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("19:30:00"), 2, 15.0, true);
	private static TActCultural tActCultRep = new TActCultural(-1, "T_UPDATE_REP", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("18:30:00"), 2, 15.0, true);
	private static TRepresentacion tRepresentacion = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("18:30:00"), 2, -1, -1, -1, true);
	private static TRepresentacion tRepresentacionRep = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("19:30:00"), 2, -1, -1, -1, true);
	private static TRepresentacion tRepresentacionMod = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("20:30:00"), 2, -1, -1, -1, true);
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "T_UPDATE", "Mike Wazowski", 2021, true);
	private static TCompañia tComp = new TCompañia(-1, "T_UPDATE", "Comedia", true);
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
		tActCultMod.setId(tActCult.getId());
		tActCultRep.setId(tActCult.getId());
		
		tRepresentacion.setIdObra(tObra.getId());
		tRepresentacion.setIdCompañia(tComp.getId());
		tRepresentacion.setIdTemporada(tTemporada.getId());
		tRepresentacion.setId(saActividad.create(tRepresentacion));
		
		tRepresentacionMod.setIdObra(tObra.getId());
		tRepresentacionMod.setIdCompañia(tComp.getId());
		tRepresentacionMod.setIdTemporada(tTemporada.getId());
		tRepresentacionMod.setId(tRepresentacion.getId());
		
		tRepresentacionRep.setIdObra(tObra.getId());
		tRepresentacionRep.setIdCompañia(tComp.getId());
		tRepresentacionRep.setIdTemporada(tTemporada.getId());
		tRepresentacionRep.setId(tRepresentacion.getId());
	}
	
	@After
	public void reiniciarActividades(){
		saActividad.update(tActCult);
		saActividad.update(tRepresentacion);
	}
	
	@Test
	public void updateActividadCulturalCorrecto(){
		int res = saActividad.update(tActCultMod);
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateRepresentacionCorrecto(){
		int res = saActividad.update(tRepresentacionMod);
		
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateActividadCulturalNoActivo(){
		saActividad.delete(tActCult.getId());
		int res = saActividad.update(tActCultMod);
		saActividad.create(tActCult);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateRepresentacionNoActivo(){		
		saActividad.delete(tRepresentacion.getId());
		int res = saActividad.update(tRepresentacionMod);
		saActividad.create(tRepresentacion);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateActividadCulturalRepetido(){
		int res = saActividad.update(tActCultRep);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateRepresentacionRepetido(){		
		int res = saActividad.update(tRepresentacionRep);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateActividadCulturalDatosIncorrectos(){
		int duracionOriginal = tActCultMod.getDuracion();
		tActCultMod.setDuracion(-3);
		
		int res = saActividad.update(tActCultMod);
		
		tActCultMod.setDuracion(duracionOriginal);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateRepresentacionDatosIncorrectos(){	
		int duracionOriginal = tRepresentacionMod.getDuracion();
		tRepresentacionMod.setDuracion(-3);
		
		int res = saActividad.update(tRepresentacionMod);
		
		tRepresentacionMod.setDuracion(duracionOriginal);
		
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void updateIdIncorrecto(){
		int idOriginal = tActCultMod.getId();
		tActCultMod.setId(-3);
		
		int res = saActividad.update(tActCultMod);
		
		tActCultMod.setId(idOriginal);
		
		try{
			assertTrue(res == -1);
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
