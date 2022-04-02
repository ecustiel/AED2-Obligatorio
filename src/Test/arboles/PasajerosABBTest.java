package arboles;

import org.junit.Test;
import arboles.PasajerosABB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class PasajerosABBTest {

    PasajerosABB pasaj = new PasajerosABB();


    @Test
    public void comprobarCi() {
        String cedula = "4.549.685";
        Pattern patron = Pattern.compile("^[0-9][.][0-9]{3}[.][0-9]{3}$");
        Matcher matcher = patron.matcher(cedula);

        assertTrue(matcher.matches());
    }
}