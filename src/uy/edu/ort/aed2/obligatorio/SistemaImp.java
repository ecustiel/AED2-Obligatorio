
package uy.edu.ort.aed2.obligatorio;

public class SistemaImp implements Sistema{

    private int maxAeropuertos;


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
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
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
