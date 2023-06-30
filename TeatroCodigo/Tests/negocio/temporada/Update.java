package negocio.temporada;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaNegocio;

public class Update {

	private static TTemporada temporada_correct = new TTemporada(-1, 1, 5, Date.valueOf("2022-06-11"),Date.valueOf("2022-06-16"),true );
	private static TTemporada temporada_mod = new TTemporada(-1, 1, 7, Date.valueOf("2022-06-11"),Date.valueOf("2022-06-16"),true );
	private static TTemporada temporada_rep = new TTemporada(-1, 2, 7, Date.valueOf("2022-06-11"),Date.valueOf("2022-06-16"),true );
	private static TTemporada temporada_datos_inc= new TTemporada(-1, 1, -7, Date.valueOf("2022-06-11"),Date.valueOf("2022-06-16"),true );
	private static SATemporada saTemporada;
	@BeforeClass
	public static void inicio()
	{
		saTemporada = FactoriaNegocio.getInstance().createSATemporada();	
		temporada_correct.setId(saTemporada.create(temporada_correct));
		temporada_mod.setId(temporada_correct.getId());
		temporada_rep.setId(saTemporada.create(temporada_rep));
	}
	@Test
	public void updateTemporadaCorrect()
	{
		int res = saTemporada.update(temporada_mod);
		try{
			assertTrue(res > 0);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@Test
	public void updateTemporadaNoActiva(){
		saTemporada.delete(temporada_correct.getId());
		int res = saTemporada.update(temporada_mod);
		saTemporada.create(temporada_correct);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@Test
	public void updateTemporadaRepetida(){
		temporada_rep.setNumTemporada(temporada_correct.getNumTemporada());
		int res = saTemporada.update(temporada_rep);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@Test
	public void updateTemporadaDatosIncorrectos(){
		int res = saTemporada.update(temporada_datos_inc);
		saTemporada.create(temporada_correct);
		try{
			assertTrue(res == -1);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	@AfterClass
	public static void destroyClass(){
		saTemporada.deleteFisico(temporada_correct.getId());
		
		saTemporada.deleteFisico(temporada_rep.getId());
	
	}
}
