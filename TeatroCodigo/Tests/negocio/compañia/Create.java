package negocio.compañia;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import negocio.factoria.FactoriaNegocio;

@RunWith(Parameterized.class)
public class Create {

    private TCompañia correct;
    private static SACompañia saCompañia;

    @Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][] {
                { new TCompañia("TestCreate01", "TestCreate01", true) }
        });
    }

    public Create(TCompañia correct) {
        this.correct = correct;
    }

    @BeforeClass
    public static void initializeClass() {
        saCompañia = FactoriaNegocio.getInstance().createSACompañia();
    }

    @Test
    public void testCorrecto() {
        int resultado = saCompañia.create(correct);
        try {
            assertTrue(resultado > 0);
        }
        catch (AssertionError assertionError) {
            fail(assertionError.getMessage());
        }
        saCompañia.deleteFisico(resultado);
    }

    @Test
    public void testRepetido() {
        int resultado = saCompañia.create(correct);
        int repetido = saCompañia.create(correct);
        try {
            assertTrue(repetido == -1);
        }
        catch (AssertionError assertionError) {
            fail(assertionError.getMessage());
        }
        saCompañia.deleteFisico(resultado);
    }

}
