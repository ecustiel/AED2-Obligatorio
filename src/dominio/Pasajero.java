package dominio;

import uy.edu.ort.aed2.obligatorio.Sistema;

public class Pasajero {
    private String nombre;
    private String cedula;
    private String telefono;
    private Sistema.Categoria categoria;


    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public Sistema.Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Sistema.Categoria categoria) {
        this.categoria = categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Pasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    }
//Categoria iria aca? clase nueva?





