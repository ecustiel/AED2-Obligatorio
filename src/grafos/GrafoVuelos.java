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
        private Aeropuerto aeropuerto;

        public Conexion (int codOrigen, int codDestino) {
            this.codOrigen = codOrigen;
            this.codDestino = codDestino;
        }
    }

    private final Aeropuerto[] aeropuertos;
    private final Conexion[][] matrizConexiones;
    private final int maxAeropuertos;
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
        if(maxAeropuertos==largo){
            return "Error 1"; //Ya hay maximo de aeropuertos
        }
        if(codigo.equals("") || nombre.equals("")){
            return "Error 2"; //Codigo o nombres son vacios o null
        }

        if(buscarAeropuerto(codigo)){
            return "Error 3"; //Ya existe un aeropuerto con ese codigo
        }
        this.aeropuertos[largo]= new Aeropuerto(codigo, nombre);
        this.largo++;
        return "Ok";
    }

    //Buscar si hay un aeropuerto registrado con ese codigo
    private boolean buscarAeropuerto(String codigoAeropuerto){
        for (int i = 0; i < largo; i++) {
            if(aeropuertos[i].codigoAeropuerto.equals(codigoAeropuerto)){
                return true;
            }
        }
        return false;
    }



}
