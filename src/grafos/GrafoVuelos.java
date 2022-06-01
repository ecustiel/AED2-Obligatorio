package grafos;


import dominio.ListaEnlazada;

import java.util.Collections;
import java.util.Objects;

public class GrafoVuelos {

    //Clase Aeropuerto
    public class Aeropuerto {
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

        @Override
        public String toString() {
            return  codigoAeropuerto + ";" + nombreAeropuerto ;
        }
    }

    //Clase Conexion
    public class Conexion {
        private boolean existe;
        private int codOrigen;
        private int codDestino;
        private double kilometros;
        private Aeropuerto aeropuerto;
        ListaEnlazada<Vuelo> listaDeVuelos = new ListaEnlazada();

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


    }

    private final Aeropuerto[] aeropuertos;
    private final Conexion[][] matrizConexiones;
    private int maxAeropuertos;
    private int largo;

    public StringBuilder getEnOrd() {
        return enOrd;
    }

    StringBuilder enOrd = new StringBuilder();

    public double getKilometrosRetorno() {
        return kilometrosRetorno;
    }

    double kilometrosRetorno;

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
        }else if(buscarVuelo(codOrigen, codDestino, codVuelo) != null){
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

    //Booleano que devuelve True si encuentra un vuelo con el mismo codigo de vuelo en la lista de la conexion
    private Vuelo buscarVuelo(String codOrigen, String codDestino, String codVuelo) {
        Aeropuerto origen = this.buscarAeropuerto(codOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);

        ListaEnlazada<Vuelo> list = this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos;

        for(int i=0; i < list.getContador(); i++){
            if(list.get(i).codVuelo.equals(codVuelo)){
                return list.get(i);
            }
        }
        return null;
    }

    //Funcion para actualizar vuelo (tratar de optimizar, mucho cidog repetido) No funciona bien la validacion del punto 6
    public String actualizarVuelo (String codOrigen, String codDestino, String codVuelo, double combustible, double minutos, double costoEnDolares){
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
        }else if(buscarVuelo(codOrigen, codDestino, codVuelo) == null){
            return "Error 6";
        }else{
            this.actualizarVueloRe(idxOrigen, idxDestino,codOrigen, codDestino, codVuelo, combustible, minutos, costoEnDolares );
            return "Ok";
        }

    }


    private void actualizarVueloRe(int idxOrigen, int idxDestino, String codOrigen, String codDestino, String codVuelo, double combustible, double minutos, double costoEnDolares){

        Vuelo nuevoVuelo = new Vuelo(idxOrigen, idxDestino, codVuelo, combustible, minutos, costoEnDolares);
        Vuelo vuelo = buscarVuelo(codOrigen, codDestino,codVuelo);
        if(this.matrizConexiones[idxOrigen][idxDestino].existe && !this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.esVacia()) {
            this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.eliminar(vuelo);
            this.matrizConexiones[idxOrigen][idxDestino].listaDeVuelos.agregarInicio(nuevoVuelo);

        }

    }


    //Listado de Aeroupuerto por cantidad de Escalas *Falta Terminar
    //Falta Optimizar sobre tdoo en el return
    public String listadoAeropuertoCantDeEscalas(String codigoAeropuertoOrigen, int cantidad){

        if(cantidad < 0 ) {
            return "Error 1";
        }else if(buscarAeropuerto(codigoAeropuertoOrigen) == null){
            return "Error 2";
        }else{
            this.listadoAeropuertoCantDeEscalasRe(codigoAeropuertoOrigen, cantidad);
            return String.valueOf(enOrd);
            //return "Ok";
        }
    }

    private ListaEnlazada<Aeropuerto> listadoAeropuertoCantDeEscalasRe(String codAeropuertoOrigen, int cantidad){
       ListaEnlazada<Aeropuerto> escalas = new ListaEnlazada<>();
        Aeropuerto aer = buscarAeropuerto(codAeropuertoOrigen);
        int idxOrigen = buscarIndiceAeropuerto(aer);
        for(int destino = 0; destino <= largo; destino++){
            if(matrizConexiones[idxOrigen][destino].existe){
                Conexion con = matrizConexiones[idxOrigen][destino];
                escalas.agregarInicio(aeropuertos[destino]);
                enOrd.append(aeropuertos[destino]);
                enOrd.append("|");

            }

        }
        enOrd.append(aer);

        return escalas;
    }



    //Viaje de costo minimo en kilometros
    public String viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino){

        Aeropuerto origen = this.buscarAeropuerto(codigoAeropuertoOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codigoAeropuertoDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);

        if(codigoAeropuertoOrigen == null || codigoAeropuertoOrigen.isEmpty() || codigoAeropuertoDestino == null || codigoAeropuertoDestino.isEmpty()) {
            return "Error 1";
        }else if(matrizConexiones[idxOrigen][idxDestino].existe){
            return "Error 2";
        }else{
            ListaEnlazada<Aeropuerto> lista = this.viajeCostoMinimoKilometrosRe(codigoAeropuertoOrigen, codigoAeropuertoDestino);

            return "Ok";

        }


    }


    private ListaEnlazada<Aeropuerto> viajeCostoMinimoKilometrosRe(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        Aeropuerto origen = this.buscarAeropuerto(codigoAeropuertoOrigen);
        Aeropuerto destino = this.buscarAeropuerto(codigoAeropuertoDestino);

        int idxOrigen = this.buscarIndiceAeropuerto(origen);
        int idxDestino = this.buscarIndiceAeropuerto(destino);
        int [] padres = new int[maxAeropuertos];
        double [] distancias = new double[maxAeropuertos];
        boolean [] visitados = new boolean[maxAeropuertos];

        for(int i=0; i < maxAeropuertos; i++){
            padres[i] = -1;
            distancias[i]= Double.MAX_VALUE;
        }

        padres[idxOrigen] = idxOrigen;
        distancias[idxOrigen] = 0;
        while(!esteTodoVisitado(visitados)) {//mientras hay algo en la frontera
            int vActual = obtenerAeropuertoNoVisitadoDeMenorCoste(visitados, distancias);
            if (distancias[vActual] < Double.MAX_VALUE) {
                for (int idxAdyacente = 0; idxAdyacente < maxAeropuertos; idxAdyacente++) { //foreach de adyacentes
                    if (matrizConexiones[vActual][idxAdyacente].existe) {
                        double distanciaAAdyacenteDesdeActual = (matrizConexiones[vActual][idxAdyacente].kilometros) + distancias[vActual];

                        if (distanciaAAdyacenteDesdeActual < distancias[idxAdyacente]) {
                            distancias[idxAdyacente] = distanciaAAdyacenteDesdeActual;
                            padres[idxAdyacente] = vActual;
                            kilometrosRetorno = distanciaAAdyacenteDesdeActual;
                        }
                    }
                }
            }
            visitados[vActual] = true;

        }

        //tabla quedo completa
        ListaEnlazada<Aeropuerto> camino = reconstruirCamino(padres, idxOrigen, idxDestino);
        return camino;
    }




    private int obtenerAeropuertoNoVisitadoDeMenorCoste(boolean[] visitados, double[] distancias) {

        double minimo = Double.MAX_VALUE;
        int idxMinimo = -1;

        for (int i = 0; i < largo; i++){
            if(distancias[i] <= minimo && !visitados[i]){
                minimo = distancias[i];
                idxMinimo = i;
            }
        }
        return idxMinimo;
    }


    private boolean esteTodoVisitado(boolean[] visitados) {
        for(int i=0; i < largo; i++){
            if(!visitados[i]) return false;
        }
        return true;
    }

    private ListaEnlazada<Aeropuerto> reconstruirCamino(int[] padres, int idxOrigen, int idxDestino) {

        ListaEnlazada<Aeropuerto> resultado = new ListaEnlazada<>();
        if(padres[idxDestino]==-1){
            return null;
        }
        int idxActual = idxDestino;
        while(idxActual != idxOrigen){
            resultado.agregarInicio(aeropuertos[idxActual]);
            enOrd.append(aeropuertos[idxActual]);
            enOrd.append("|");
            idxActual = padres[idxActual];
        }

        resultado.agregarInicio(aeropuertos[idxOrigen]);

        enOrd.append(aeropuertos[idxOrigen]);
        enOrd.append("|");
        enOrd.deleteCharAt(enOrd.length()-1);

        return resultado;

    }


}
