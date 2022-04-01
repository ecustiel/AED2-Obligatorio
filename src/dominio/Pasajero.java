package dominio;

public class Pasajero {
    private String nombre;
    private String cedula;
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
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

    public Pasajero(String nombre, String cedula, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }


}
