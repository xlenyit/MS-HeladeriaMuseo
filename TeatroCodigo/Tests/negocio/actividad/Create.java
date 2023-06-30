package negocio.actividad;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.compañia.SACompañia;
import negocio.compañia.TCompañia;
import negocio.factoria.FactoriaNegocio;
import negocio.obra.SAObra;
import negocio.obra.TObra;
import negocio.temporada.SATemporada;
import negocio.temporada.TTemporada;

public class Create {

	private static TRepresentacion tRepresentacion_correcta = new TRepresentacion(-1, 2, 10,  Date.valueOf("2022-06-11"),  Date.valueOf("2022-06-16"), Time.valueOf("16:30:00"),
			30, -1, -1, -1, true);
	private static TRepresentacion tRepresentacion_incorrecta = new TRepresentacion(-1, 2, 10,  Date.valueOf("2022-06-11"),  Date.valueOf("2020-06-16"), Time.valueOf("16:30:00"),
			30, -1, -1, -1, true);
	private static TActCultural tActividad_cultural_correcta = new TActCultural(-1, "TCreate", 12, Date.valueOf("2022-06-11"), Date.valueOf("2022-06-20"), Time.valueOf("17:30:00"), 2, 15.0, true);
	private static TActCultural tActividad_cultural_incorrecta = new TActCultural(-1, "TCreate", 12, Date.valueOf("2022-06-11"), Date.valueOf("2020-06-20"), Time.valueOf("16:30:00"), 2, 15.0, true);
	private static SAActividad saActividad;
	private static SAObra saObra;
	private static SACompañia saCompañia;
	private static SATemporada saTemporada;
	private static TObra tObra = new TObra(-1, "Comedia", "Va un tio y se cae", "TCreate", "Mike Wazowski", 2021, true);
    private static TCompañia tComp = new TCompañia(-1, "TCreate", "Comedia", true);
    private static TTemporada tTemporada = new TTemporada(-1, 19, 4, Date.valueOf("2021-06-11"), Date.valueOf("2022-06-20"), true);
	
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
	    tRepresentacion_correcta.setIdObra(tObra.getId());
		tRepresentacion_correcta.setIdCompañia(tComp.getId());
		tRepresentacion_correcta.setIdTemporada(tTemporada.getId());
	}
	
	@Test
	public void create_representacion_correct() {
		int id = saActividad.create(tRepresentacion_correcta);
		try
		{
			assertTrue(id > 0);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		tRepresentacion_correcta.setId(id);
	}
	@Test
	public void create_representacion_repetido() {
		int id = saActividad.create(tRepresentacion_correcta);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	@Test
	public void create_representacion_incorrect() {
		int id = saActividad.create(tRepresentacion_incorrecta);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	@Test
	public void create_activida_cultural_correct() {
		int id = saActividad.create(tActividad_cultural_correcta);
		try
		{
			assertTrue(id > 0);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
		tActividad_cultural_correcta.setId(id);
	}
	@Test
	public void create_activida_cultural_repetido() {
		int id = saActividad.create(tActividad_cultural_correcta);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}
	@Test
	public void create_activida_cultural_incorrect() {
		int id = saActividad.create(tActividad_cultural_incorrecta);
		try
		{
			assertTrue(id == -1);
		}
		catch(Exception ae)
		{
			fail(ae.getMessage());
		}
	}

	@AfterClass
	public static void destroyClass()
	{	
		saActividad.deleteFisico(tRepresentacion_correcta.getId());
    	saActividad.deleteFisico(tActividad_cultural_correcta.getId());
		saObra.deleteFisico(tObra.getId());
	    saCompañia.deleteFisico(tComp.getId());
	    saTemporada.deleteFisico(tTemporada.getId());
	}
}
