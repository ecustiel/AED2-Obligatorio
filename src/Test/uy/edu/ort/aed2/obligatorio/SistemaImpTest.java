package uy.edu.ort.aed2.obligatorio;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SistemaImpTest {


    private Retorno ret;
    private SistemaImp sistem;


    @Before
    public void setUp() throws Exception {
        sistem = new SistemaImp();
    }

    @Test
    public void inicializarSistema() {
        assertEquals(Retorno.Resultado.OK, sistem.inicializarSistema(1).resultado);

    }

    @Test
    public void registrarPasajero() {
    }

    @Test
    public void buscarPasajero() {
    }

    @Test
    public void listarPasajerosAscendente() {
    }

    @Test
    public void listarPasajerosDescendente() {
    }

    @Test
    public void listarPasajerosPorCategoría() {
    }

    @Test
    public void registrarAeropuerto() {
    }

    @Test
    public void registrarConexion() {
    }

    @Test
    public void registrarVuelo() {
    }

    @Test
    public void actualizarVuelo() {
    }

    @Test
    public void listadoAeropuertosCantDeEscalas() {
    }

    @Test
    public void viajeCostoMinimoKilometros() {
    }

    @Test
    public void viajeCostoMinimoDolares() {
    }

    @Test
    public void consultaDisponibilidad() {
    }
}