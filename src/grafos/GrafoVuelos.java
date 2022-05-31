package grafos;


import dominio.ListaEnlazada;

import java.util.Objects;

public class GrafoVuelos {

    //Clase Aeropuerto
    private class Aeropuerto {
        private String codigoAeropuerto;
        private String nombreAeropuerto;
        private boolean estado;

        public Aeropuerto(String codigoAeropuerto, String nombreAeropuerto) {
            this.codigoAeropuerto = codigoAeropuerto;
            this.nombreAeropuerto = nombreAeropuerto;
            this.estado = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Aeropuerto aeropuerto = (Aeropuerto) o;
            return Objects.equals(codigoAeropuerto, aeropuerto.codigoAeropuerto);
        }
    }

    //Clase Conexion
    private class Conexion {
        private boolean existe;
        private int codOrigen;
        private int codDestino;
        private double kilometros;
        private Aeropuerto aeropuerto;
        ListaEnlazada listaDeVuelos = new ListaEnlazada();

        public Conexion (int codOrigen, int codDestino) {
            this.codOrigen = codOrigen;
            this.codDestino = codDestino;
        }
    }

    //Clase Vuelo
    public class Vuelo {
        private int codOrigen;
        private int codDestino;
        private String codVuelo;
        private double combustible;
        private double minutos;
        private double costoEnDolares;

        public Vuelo (int codOrigen, int codDestino, String codVuelo, double combustible, double minutos, double costoEnDolares){
            this.codOrigen = codOrigen;
            this.codDestino = codDestino;
            this.codVuelo = codVuelo;
            this.combustible = combustible;
            this.minutos = minutos;
            this.costoEnDolares = costoEnDolares;
        }

        public String getCodVuelo() {
            return codVuelo;
        }

        //@Override
        public boolean equals(String o) {
            if (this.codVuelo.equals(o) ) return true;
            return false;
            //if (o == null || getClass() != o.getClass()) return false;
           // Vuelo vuelo = Vuelo;
            //return Objects.equals(codVuelo, vuelo.codVuelo);
        }
    }

    private final Aeropuerto[] aeropuertos;
    private final Conexion[][] matrizConexiones;
    private int maxAeropuertos;
    private int largo;

    public GrafoVuelos(int maxAeropuertos){
        this.maxAeropuertos = maxAeropuertos;
        this.aeropuertos=new Aeropuerto[maxAeropuertos];
        this.matrizConexiones=new Conexion[maxAeropuertos][maxAeropuertos];
        for (int i = 0; i < maxAeropuertos; i++) {
            for (int j = 0; j < maxAeropuertos; j++) {

                this.matrizConexiones[i][j]=new Conexion(i,j);
            }

        }
    }

    //Agregando Aeropuerto
    public String agregarAeropuerto(String codigo, String nombre) {
        if(largo==maxAeropuertos){
            return "Error 1"; //Ya hay maximo de aeropuertos
        }else if(nombre == null || codigo == null || codigo.isEmpty() || nombre.isEmpty()){
                return "Error 2"; //Codigo o nombres son vacios o null
        }else if(buscarAeropuerto(codigo) != null){
            return "Error 3"; //Ya existe un aeropuerto con ese codigo
        }
        this.aeropuertos[largo]= new Aeropuerto(codigo, nombre);
        this.largo++;
        return "Ok";
    }

    //Buscar si hay un aeropuerto registrado con ese codigo y devuelve aeropuerto
    private Aeropuerto buscarAeropuerto(String codigoAeropuerto){

        for (int i = 0; i < largo; i++) {
            if(aeropuertos[i].codigoAeropuerto.equals(codigoAeropuerto)){
                return new Aeropuerto(aeropuertos[i].nombreAeropuerto, aeropuertos[i].codigoAeropuerto);
            }
        }
        //return codigo;
        return null;
    }

    //Busca el aeropuerto por indice en los aeropuertos a ver si existe
    private int buscarIndiceAeropuerto(Aeropuerto aer){
        if(aer != null) {
            for (int i = 0; i < aeropuertos.length - 1; i++) {
                if (aeropuertos[i].codigoAeropuerto.equals(aer.nombreAeropuerto)) {
                    return i;
                }
            }
        }
        return -1;
    }

    //Buscar si existe conexion entre 2 aeropuertos (Si se puede optimizar, faltan validaciones)
    private boolean existe(String codOrigen, String codDestino){
        Aeropuerto origen = this.buscarAeropuerto(codOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);

        return matrizConexiones[idxOrigen][idxDestino].existe;
    }


    //Agregar Conexion (Tratar de optimizar si se puede) Faltan Validaciones
    public String agregarConexion(String codAeropuertoOrigen, String codAeropuertoDestino, double kilometros){

        Aeropuerto origen = this.buscarAeropuerto(codAeropuertoOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codAeropuertoDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);

        if(kilometros <= 0){
            return "Error 1";
        }else if(origen == null){
            return "Error 2";
        }else if(destino == null){
            return "Error 3";
        }else if(this.matrizConexiones[idxOrigen][idxDestino].existe){
            return "Error 4";
        }else{
            this.agregarConexion(idxOrigen, idxDestino, kilometros);
            return "Ok";
        }

    }

    //Faltan Validaciones
    private void agregarConexion(int idxOrigen, int idxDestino, double kilometros) {

        this.matrizConexiones[idxOrigen][idxDestino].existe=true;
        this.matrizConexiones[idxOrigen][idxDestino].kilometros=kilometros;

    }

    //Agregar Vuelo (tratar de optimizar, faltan validaciones)
    public String agregarVuelo (String codOrigen, String codDestino, String codVuelo, double combustible, double minutos, double costoEnDolares){
        Aeropuerto origen = this.buscarAeropuerto(codOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);

        if(combustible <= 0 || minutos <= 0 || costoEnDolares <= 0 ) {
            return "Error 1";
        }else if(codOrigen == null || codOrigen.isEmpty() || codDestino == null || codDestino.isEmpty() || codVuelo == null || codVuelo.isEmpty()){
            return "Error 2";
        }else if(buscarAeropuerto(codOrigen) == null){
            return "Error 3";
        }else if(buscarAeropuerto(codDestino) == null){
            return "Error 4";
        }else if(!this.matrizConexiones[idxOrigen][idxDestino].existe){
            return "Error 5";
        }else if(this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.obtenerVuelo(codVuelo)){
            return "Error 6";
        }else{
            this.agregarVuelo(idxOrigen, idxDestino, codVuelo, combustible, minutos, costoEnDolares );
            return "Ok";
        }

    }

    //revisar si es necesario agregar al final
    private void agregarVuelo(int idxOrigen, int idxDestino, String codVuelo, double combustible, double minutos, double costoEnDolares){

        Vuelo vuelo = new Vuelo(idxOrigen, idxDestino,codVuelo,combustible,minutos,costoEnDolares);

        if(this.matrizConexiones[idxOrigen][idxDestino].existe && this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.esVacia()) {
            this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.agregarInicio(vuelo);
        }

    }





}
