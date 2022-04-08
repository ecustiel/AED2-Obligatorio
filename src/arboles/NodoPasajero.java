package arboles;

import dominio.Pasajero;
import uy.edu.ort.aed2.obligatorio.Sistema;

public class NodoPasajero {


    //private NodoPasajero pasaj;
    private Pasajero pasajero;
    private NodoPasajero izq;
    private NodoPasajero der;

    //Constructor
    public NodoPasajero(String nombre, String cedula, String telefono, Sistema.Categoria categoria){
        this.pasajero = new Pasajero(nombre, cedula, telefono, categoria);
        izq = null;
        der = null;
    }

    public NodoPasajero(String nombre, String cedula, String telefono, Sistema.Categoria categoria, NodoPasajero i, NodoPasajero d){
        this.pasajero = new Pasajero(nombre, cedula, telefono, categoria);
        izq = i;
        der = d;
    }

    //Getters y Setters
    public Pasajero getPasajero() {
        return pasajero;
    }

    public NodoPasajero getIzq() {
        return izq;
    }

    public void setIzq(NodoPasajero izq) {
        this.izq = izq;
    }

    public NodoPasajero getDer() {
        return der;
    }

    public void setDer(NodoPasajero der) {
        this.der = der;
    }

}
