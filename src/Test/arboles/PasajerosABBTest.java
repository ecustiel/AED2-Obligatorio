package arboles;

import org.junit.Test;
import arboles.PasajerosABB;
import uy.edu.ort.aed2.obligatorio.Sistema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasajerosABBTest {

    PasajerosABB pasaj = new PasajerosABB();
    Sistema.Categoria cat;




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
    public void insertarPasajero() {
        PasajerosABB nuevo = new PasajerosABB();

        System.out.println(nuevo.insertarPasajero("51211024","Federico", "1234567", Sistema.Categoria.A));
        System.out.println(nuevo.insertarPasajero("4.549.685-2","Eduardo", "1234567", Sistema.Categoria.A));
        System.out.println(nuevo.insertarPasajero("3.456.925-6","Claudia", "1234567", Sistema.Categoria.B));
        System.out.println(nuevo.insertarPasajero("1.828.166-6","Lalo", "1234567", Sistema.Categoria.C));
        System.out.println(nuevo.insertarPasajero("121.102-4","Charlie", "1234567", Sistema.Categoria.C));

        System.out.println(nuevo.listarAscendente());
    }

    @Test
    public void buscarPasajero() {
        PasajerosABB nuevo = new PasajerosABB();
        assertEquals("Insertado Correctamente Nuevo", nuevo.insertarPasajero("5.121.102-4","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("4.549.685-2","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("5.153.386-4","Gerardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("1.828.166-6","Federico", "098616262", Sistema.Categoria.B));

        System.out.print(nuevo.buscarPasajero("4.549.685-2"));
        System.out.print(nuevo.buscarPasajero("4.549.685-2"));
        System.out.print(nuevo.buscarPasajero("4.549.685-2"));

    }

    @Test
    public void listarAscendente() {
        PasajerosABB nuevo = new PasajerosABB();
        assertEquals("Insertado Correctamente Nuevo", nuevo.insertarPasajero("5.121.102-4","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("4.549.685-2","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("5.153.386-4","Gerardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("1.828.166-6","Federico", "098616262", Sistema.Categoria.B));

        System.out.print(nuevo.listarAscendente());
    }

    @Test
    public void listaDescendente() {

        PasajerosABB nuevo = new PasajerosABB();
        assertEquals("Insertado Correctamente Nuevo", nuevo.insertarPasajero("5.121.102-4","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("4.549.685-2","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("5.153.386-4","Gerardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("1.828.166-6","Federico", "098616262", Sistema.Categoria.B));

        System.out.print(nuevo.listaDescendente());
    }


    @Test
    public void listarXCategoria() {
        PasajerosABB nuevo = new PasajerosABB();
        assertEquals("Insertado Correctamente Nuevo", nuevo.insertarPasajero("5.121.102-4","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("4.549.685-2","Eduardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("5.153.386-4","Gerardo", "1234567", Sistema.Categoria.A));
        assertEquals("Insertado Correctamente Nodo", nuevo.insertarPasajero("1.828.166-6","Federico", "098616262", Sistema.Categoria.B));

        System.out.println(nuevo.listarXCategoria(Sistema.Categoria.A.getTexto()));


    }
}