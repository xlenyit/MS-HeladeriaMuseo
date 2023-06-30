package negocio.temporada;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.actividad.SAActividad;
import negocio.actividad.TRepresentacion;
import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;

public class Delete {
	private static SATemporada saTemporada;
	private static SAActividad saActividad;
	private static SAObra saObra;
	private static SACompañia saCompañia;

    private static TTemporada tTemporada = new TTemporada(-1, 19, 4, Date.valueOf("2021-06-11"), Date.valueOf("2022-06-20"), true);
    private static TRepresentacion tRepresentacion = new TRepresentacion(-1, 2, 10,  Date.valueOf("2022-06-11"),  Date.valueOf("2022-06-16"), Time.valueOf("16:30:00"), 30, -1, -1, -1, true);
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "TDelete", "Mike Wazowski", 2021, true);
    private static TCompañia tComp = new TCompañia(-1, "TDelete", "Comedia", true);
    
	@BeforeClass
	public static void init()
	{
		saActividad = FactoriaNegocio.getInstance().createSAActividad();	
		saObra = FactoriaNegocio.getInstance().createSAObra();
		saTemporada = FactoriaNegocio.getInstance().createSATemporada();
		saCompañia = FactoriaNegocio.getInstance().createSACompañia();
		
		tObra.setId(saObra.create(tObra));
	    tComp.setId(saCompañia.create(tComp));
	    tTemporada.setId(saTemporada.create(tTemporada));
	    
	    tRepresentacion.setIdObra(tObra.getId());
	    tRepresentacion.setIdCompañia(tComp.getId());
	    tRepresentacion.setIdTemporada(tTemporada.getId());
	}
	
	@After
	public void reiniciarTemporada()
	{
		saTemporada.create(tTemporada);
	}
	
	@Test
	public void deleteTemporadaCorrecto() {
		int res = saTemporada.delete(tTemporada.getId());
		
		try
		{
			assertTrue(res > 0);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void deleteTemporadaConRepresentacion() {
		tRepresentacion.setId(saActividad.create(tRepresentacion));
		int res = saTemporada.delete(tTemporada.getId());
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		
		saActividad.delete(tRepresentacion.getId());
	}
	
	@Test
	public void deleteTemporadaIncorrecto() {
		int res = saTemporada.delete(-3);
		
		try
		{
			assertTrue(res == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass()
	{	
		saActividad.deleteFisico(tRepresentacion.getId());
		saObra.deleteFisico(tObra.getId());
	    saCompañia.deleteFisico(tComp.getId());
	    
	    saTemporada.deleteFisico(tTemporada.getId());
	}

}
