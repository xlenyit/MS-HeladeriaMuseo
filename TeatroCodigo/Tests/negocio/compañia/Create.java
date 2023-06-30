package negocio.compa�ia;

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

    private TCompa�ia correct;
    private static SACompa�ia saCompa�ia;

    @Parameters
    public static Iterable<Object[]> getData() {
        return Arrays.asList(new Object[][] {
                { new TCompa�ia("TestCreate01", "TestCreate01", true) }
        });
    }

    public Create(TCompa�ia correct) {
        this.correct = correct;
    }

    @BeforeClass
    public static void initializeClass() {
        saCompa�ia = FactoriaNegocio.getInstance().createSACompa�ia();
    }

    @Test
    public void testCorrecto() {
        int resultado = saCompa�ia.create(correct);
        try {
            assertTrue(resultado > 0);
        }
        catch (AssertionError assertionError) {
            fail(assertionError.getMessage());
        }
        saCompa�ia.deleteFisico(resultado);
    }

    @Test
    public void testRepetido() {
        int resultado = saCompa�ia.create(correct);
        int repetido = saCompa�ia.create(correct);
        try {
            assertTrue(repetido == -1);
        }
        catch (AssertionError assertionError) {
            fail(assertionError.getMessage());
        }
        saCompa�ia.deleteFisico(resultado);
    }

}
