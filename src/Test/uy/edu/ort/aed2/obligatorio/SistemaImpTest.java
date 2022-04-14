package uy.edu.ort.aed2.obligatorio;

import arboles.NodoPasajero;
import arboles.PasajerosABB;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SistemaImpTest {


    private Retorno ret;
    private SistemaImp sistem;
    private Sistema sis;


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

        assertEquals(Retorno.Resultado.OK, sistem.registrarPasajero("4.549.685-2", "Eduardo", "098616262", Sistema.Categoria.A).resultado);
        assertEquals(Retorno.Resultado.ERROR_1, sistem.registrarPasajero("", "Eduardo", "098616262", Sistema.Categoria.A).resultado);

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