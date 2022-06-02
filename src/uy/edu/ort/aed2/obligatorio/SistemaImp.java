
package uy.edu.ort.aed2.obligatorio;

import arboles.PasajerosABB;
import dominio.ListaEnlazada;
import grafos.GrafoVuelos;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SistemaImp<T> implements Sistema{

    private int maxAeropuertos;
    private PasajerosABB raiz = new PasajerosABB();
    private GrafoVuelos grafo = new GrafoVuelos(100);
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
        int contador = raiz.contadorNodos;

        if(!match.find()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else {
            StringBuilder res = new StringBuilder();
            res.append(raiz.buscarPasajero(cedula).toString());
            res.deleteCharAt(res.length()-1);
            String resultado = String.valueOf(res);

            //String resultado = raiz.buscarPasajero(cedula).toString();

            if(resultado.isEmpty()){
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
        if(listaPasajeros == null){
            listaPasajeros = "";
        }
            Retorno ret = new Retorno(Retorno.Resultado.OK, 0, listaPasajeros);
            ret.valorString = listaPasajeros;
            return ret;

    }

    @Override
    public Retorno listarPasajerosDescendente() {

        String listaPasajeros = this.raiz.listaDescendente();
        if(listaPasajeros == null){
            listaPasajeros = "";
        }
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, listaPasajeros);
        ret.valorString = listaPasajeros;
        return ret;
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        String listaPasajeros = this.raiz.listarXCategoria(categoria.getTexto());
        if(listaPasajeros == null){
            listaPasajeros = "";
        }
        Retorno ret = new Retorno(Retorno.Resultado.OK, 0, listaPasajeros);
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
        String retorno = grafo.agregarConexion(codigoAeropuertoOrigen,codigoAeropuertoDestino,kilometros);
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")){
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Error 3")){
            return new Retorno(Retorno.Resultado.ERROR_3);
        }else if(retorno.equals("Error 4")){
            return new Retorno(Retorno.Resultado.ERROR_4);
        }else if(retorno.equals("Ok")){
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        String retorno = grafo.agregarVuelo(codigoAeropuertoOrigen,codigoAeropuertoDestino,codigoDeVuelo,combustible,minutos, costoEnDolares);
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")){
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Error 3")){
            return new Retorno(Retorno.Resultado.ERROR_3);
        }else if(retorno.equals("Error 4")) {
            return new Retorno(Retorno.Resultado.ERROR_4);
        }else if(retorno.equals("Error 5")) {
            return new Retorno(Retorno.Resultado.ERROR_5);
        }else if(retorno.equals("Error 6")){
            return new Retorno(Retorno.Resultado.ERROR_6);
        }else if(retorno.equals("Ok")){
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        String retorno = grafo.actualizarVuelo(codigoAeropuertoOrigen,codigoAeropuertoDestino,codigoDeVuelo,combustible,minutos, costoEnDolares);
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")){
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Error 3")){
            return new Retorno(Retorno.Resultado.ERROR_3);
        }else if(retorno.equals("Error 4")) {
            return new Retorno(Retorno.Resultado.ERROR_4);
        }else if(retorno.equals("Error 5")) {
            return new Retorno(Retorno.Resultado.ERROR_5);
        }else if(retorno.equals("Error 6")){
            return new Retorno(Retorno.Resultado.ERROR_6);
        }else if(retorno.equals("Ok")){
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        String retorno = grafo.listadoAeropuertoCantDeEscalas(codigoAeropuertoDeOrigen, cantidad);
        String ordenada = String.valueOf(grafo.getEnOrd());
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else /*if(retorno.equals("Ok")*/{
            //return new Retorno(Retorno.Resultado.OK);
            Retorno ret = new Retorno(Retorno.Resultado.OK, 0, ordenada);
            ret.valorString = retorno;
            return ret;
            //return new Retorno(Retorno.Resultado.OK);
        }
       //return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        String retorno =  grafo.viajeCostoMinimoKilometros(codigoAeropuertoOrigen, codigoAeropuertoDestino);
        int valorEntero = (int) grafo.getKilometrosRetorno();
        String valorString = String.valueOf(grafo.getEnOrd());
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Ok")){
            Retorno ret = new Retorno(Retorno.Resultado.OK, valorEntero, valorString);
            ret.valorString = valorString;
            ret.valorEntero = valorEntero;
            return ret;
            //return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        String retorno =  grafo.viajeCostoMinimoDolares(codigoAeropuertoOrigen, codigoAeropuertoDestino);
        int valorEntero = (int) grafo.getCostoMinimoDolaresRetorno();
        String valorString = String.valueOf(grafo.getEnOrd());
        if(retorno.equals("Error 1")){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }else if(retorno.equals("Error 2")) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }else if(retorno.equals("Ok")){
            Retorno ret = new Retorno(Retorno.Resultado.OK, valorEntero, valorString);
            ret.valorString = valorString;
            ret.valorEntero = valorEntero;
            return ret;
            //return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

}
