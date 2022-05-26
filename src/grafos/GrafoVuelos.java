package grafos;


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

        public Conexion (int codOrigen, int codDestino) {
            this.codOrigen = codOrigen;
            this.codDestino = codDestino;
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


    //Agregar Conexion (Tratar de optimizar si se puede)
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

    private void agregarConexion(int idxOrigen, int idxDestino, double kilometros) {

        this.matrizConexiones[idxOrigen][idxDestino].existe=true;
        this.matrizConexiones[idxOrigen][idxDestino].kilometros=kilometros;
    }


}
