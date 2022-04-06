package arboles;

import org.junit.Test;
import arboles.PasajerosABB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasajerosABBTest {

    PasajerosABB pasaj = new PasajerosABB();


    @Test
    public void validateCi() {


        assertTrue(pasaj.validateCi("1.095.871-8"));
        assertTrue(pasaj.validateCi("6032655"));
        assertFalse(pasaj.validateCi("6032652"));
        assertFalse(pasaj.validateCi("17367033"));
    }

    @Test
    public void cleanCi() {

        String expected = "45022186";

        String withDotsAndMiddleSlash = pasaj.cleanCi("4.502.218-6");
        String withSpecialCharacters = pasaj.cleanCi("4$502%2/18?! .-^()#@=6");
        String withLetters = pasaj.cleanCi("A4.502.AAA218-6B");

        assertEquals(expected, withDotsAndMiddleSlash);
        assertEquals(expected, withSpecialCharacters);
        assertEquals(expected, withLetters);
    }

    @Test
    public void validationDigit() {
        assertEquals(8, pasaj.validationDigit("9.105.702-8"));
        assertEquals(4, pasaj.validationDigit("918.596-4"));
        assertEquals(6, pasaj.validationDigit("45022186"));
        assertEquals(5, pasaj.validationDigit("6032655"));
        assertEquals(1, pasaj.validationDigit("251821"));

    }
}