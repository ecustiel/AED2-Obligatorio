package arboles;

import dominio.Pasajero;

public class NodoPasajero {


    private Pasajero pasajero;
    private NodoPasajero izq;
    private NodoPasajero der;

    //Constructor
    public NodoPasajero(String nombre, String cedula, String telefono){
        pasajero = new Pasajero(nombre, cedula, telefono);
        izq = null;
        der = null;
    }

    public NodoPasajero(String nombre, String cedula, String telefono, NodoPasajero i, NodoPasajero d){
        pasajero = new Pasajero(nombre, cedula, telefono);
        izq = i;
        der = d;
    }

    //Getters y Setters
    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
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
