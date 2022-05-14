package arboles;

import dominio.Pasajero;

public class NodoPasajero<T> {


    //private NodoPasajero pasaj;
    private Pasajero pasajero;
    private NodoPasajero izq;
    private NodoPasajero der;

    //Constructor
    public NodoPasajero(T nombre, T cedula, T telefono, T categoria){
        this.pasajero = new Pasajero(nombre, cedula, telefono, categoria);
        izq = null;
        der = null;
    }

    public NodoPasajero(T nombre, T cedula, T telefono, T categoria, NodoPasajero i, NodoPasajero d){
        this.pasajero = new Pasajero(nombre, cedula, telefono, categoria);
        izq = i;
        der = d;
    }

    //Getters y Setters
    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) { this.pasajero = pasajero;}

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
