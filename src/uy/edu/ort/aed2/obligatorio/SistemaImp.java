
package uy.edu.ort.aed2.obligatorio;

import arboles.NodoPasajero;
import arboles.PasajerosABB;
import dominio.Pasajero;
import grafos.GrafoVuelos;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SistemaImp<T> implements Sistema{

    private int maxAeropuertos = 0;
    private PasajerosABB raiz = new PasajerosABB();
    private GrafoVuelos grafo = new GrafoVuelos(5);
    Pattern pattern = Pattern.compile("^[1-9]?.?[0-9]{3}.[0-9]{3}-[1-9]$");


    @Override
    public Retorno inicializarSistema(int maxAeropuertos) {

        if(maxAeropuertos > 2) {
            this.maxAeropuertos = maxAeropuertos;
            return new Retorno(Retorno.Resultado.OK);
        }else{
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {

        String retorno = raiz.insertarPasajero(cedula, nombre, telefono, categoria.A).toString();

        if(retorno.equals("Error1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error2")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Error3")) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        } else if (retorno.equals("Insertado Correctamente Nuevo") || retorno.equals("Insertado Correctamente Nodo")) {
            return new Retorno(Retorno.Resultado.OK);
        }else{
            return new Retorno(Retorno.Resultado.OK);
        }


    }

    @Override
    public Retorno buscarPasajero(String cedula) {

        Matcher match = pattern.matcher(cedula);

        if(!match.find()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else {
            String resultado = raiz.buscarPasajero(cedula).toString();

            if(resultado == null){
                return new Retorno(Retorno.Resultado.ERROR_2);
            }else{
                return new Retorno(Retorno.Resultado.OK, raiz.contadorNodos, resultado);
            }
        }


        //return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosAscendente() {

        String listaPasajeros = this.raiz.listarAscendente();
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = listaPasajeros;
        return ret;
    }

    @Override
    public Retorno listarPasajerosDescendente() {

        String listaPasajeros = this.raiz.listaDescendente();
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = listaPasajeros;
        return ret;
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        String listaPasajeros = this.raiz.listarXCategoria(categoria.getTexto());
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = listaPasajeros;
        return ret;
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {

        String retorno = grafo.agregarAeropuerto(codigo, nombre);
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")){
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Error 3")){
            return new Retorno(Retorno.Resultado.ERROR_3);
        }else if(retorno.equals("Ok")){
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

}
