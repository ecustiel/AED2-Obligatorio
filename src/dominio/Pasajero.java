package dominio;

import uy.edu.ort.aed2.obligatorio.Sistema;

public class Pasajero<T> {
    private T nombre;
    private T cedula;
    private T telefono;
    private T categoria;


    public T getNombre() {
        return nombre;
    }

    public T getCedula() {
        return cedula;
    }

    public T getTelefono() {
        return telefono;
    }

    public T getCategoria() {
        return categoria;
    }

    public void setCategoria(T categoria) {
        this.categoria = categoria;
    }

    public void setNombre(T nombre) {
        this.nombre = nombre;
    }

    public void setCedula(T cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(T telefono) {
        this.telefono = telefono;
    }

    public Pasajero(T cedula, T nombre, T telefono, T categoria) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    }
//Categoria iria aca? clase nueva?





