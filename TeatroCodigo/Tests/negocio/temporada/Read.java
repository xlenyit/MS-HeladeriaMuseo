package negocio.temporada;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import negocio.factoria.FactoriaAbstractaNegocio;

public class Read {
	private static TTemporada tTemporada = new TTemporada(-1, 37, 4, Date.valueOf("2022-08-11"), Date.valueOf("2022-08-20"), true);
	
	private static SATemporada saTemporada;
	
	@BeforeClass
	public static void inicio(){
		saTemporada = FactoriaAbstractaNegocio.getInstance().createSATemporada();
		
		tTemporada.setId(saTemporada.create(tTemporada));
	}
	
	@Test
	public void buscarTemporadaCorrecto(){
		TTemporada res = saTemporada.read(tTemporada.getId());
		
		try{
			assertNotNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarTemporadaNoActivo(){
		saTemporada.delete(tTemporada.getId());
		TTemporada res = saTemporada.read(tTemporada.getId());
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@Test
	public void buscarIdIncorrecto(){
		TTemporada res = saTemporada.read(-1);
		
		try{
			assertNull(res);
		}catch(Exception ae){
			fail(ae.getMessage());
		}
	}
	
	@AfterClass
	public static void destroyClass(){
		saTemporada.deleteFisico(tTemporada.getId());
	}
}
