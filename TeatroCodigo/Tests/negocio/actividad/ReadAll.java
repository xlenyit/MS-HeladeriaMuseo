package negocio.actividad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.compa�ia.SACompa�ia;
import negocio.compa�ia.TCompa�ia;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;


public class ReadAll {

	private static TActCultural tActCult = new TActCultural(-1, "T_READ_ALL", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("19:30:00"), 2, 15.0, true);
	private static TRepresentacion tRepresentacion = new TRepresentacion(-1, 12, 15.0, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("18:30:00"), 2, -1, -1, -1, true);
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "T_READ_ALL", "Mike Wazowski", 2021, true);
	private static TCompa�ia tComp = new TCompa�ia(-1, "T_READ_ALL", "Comedia", true);
	private static TTemporada tTemporada = new TTemporada(-1, 37, 4, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), true);
	
	private static SAActividad saActividad;
	private static SAObra saObra;
	private static SACompa�ia saCompa�ia;
	private static SATemporada saTemporada;
	
	@BeforeClass
	public static void inicio(){
		saActividad = FactoriaAbstractaNegocio.getInstance().createSAActividad();
		saObra = FactoriaAbstractaNegocio.getInstance().createSAObra();
		saCompa�ia = FactoriaAbstractaNegocio.getInstance().createSACompa�ia();
		saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		
		tObra.setId(saObra.create(tObra));
		tComp.setId(saCompa�ia.create(tComp));
		tTemporada.setId(saTemporada.create(tTemporada));
		
		tActCult.setId(saActividad.create(tActCult));
		
		tRepresentacion.setIdObra(tObra.getId());
		tRepresentacion.setIdCompa�ia(tComp.getId());
		tRepresentacion.setIdTemporada(tTemporada.getId());
		tRepresentacion.setId(saActividad.create(tRepresentacion));
	}
	
	@Test
	public void buscarActCultCorrecto(){
		Collection<TActividad> res = saActividad.readAll();
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarRepresentacionCorrecto(){
		Collection<TActividad> res = saActividad.readAll();
		
		try{
			assertFalse(res.isEmpty());
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saActividad.deleteFisico(tActCult.getId());
		
		saActividad.deleteFisico(tRepresentacion.getId());
		
		saObra.deleteFisico(tObra.getId());
		saCompa�ia.deleteFisico(tComp.getId());
		saTemporada.deleteFisico(tTemporada.getId());
	}
}
