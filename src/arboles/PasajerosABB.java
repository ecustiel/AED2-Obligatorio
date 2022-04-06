package arboles;

//import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasajerosABB {

    //Data
    private NodoPasajero pasajeros;

    //Getter
    public NodoPasajero getPasajeros() {
        return pasajeros;
    }


    //Funciones para Comprobar validez de Cedula (Formato 4.549.685-2)
    //Tambien valida el digito despues del guion
    public boolean validateCi(String cedula) {
        String cleanCi = this.cleanCi(cedula);
        char validationDigit = cleanCi.charAt(cleanCi.length() - 1);

        return Character.getNumericValue(validationDigit) == this.validationDigit(cleanCi);
    }

    public String cleanCi(String cedula) {
        return cedula.replaceAll("[^0-9]", "");
    }

    protected Integer validationDigit(String cedula) {
        String cleanCi = this.cleanCi(cedula);
        int a = 0;
        String baseNumber = "2987634";
        String addZeros = String.format("%8s", cleanCi).replace(" ", "0");

        for (int i = 0; i < baseNumber.length(); i++) {
            int baseDigit = Character.getNumericValue(baseNumber.charAt(i));
            int ciWithZeros = Character.getNumericValue(addZeros.charAt(i));
            a += (baseDigit * ciWithZeros) % 10;
        }
        return a % 10 == 0 ? 0 : 10 - a % 10;
    }



    //Insertar Pasajero
    //public String insertarPasajero(String cedula, String nombre, String telefrono, Categoria categoria) {

    //}


    //Comprobar Si Existe y devolverlo, hacemos uso de la funcion pasajeroExiste
    public String buscarPasajero(String cedula){

        if(validateCi(cedula)){
            return pasajeroExiste(cedula, this.pasajeros);
        }else{
            return "No Existe"; //Devuelvo no existe?
        }

    }


    //Contador de Nodos
    public int cantidadNodos(NodoPasajero pas){
        int cont = 0;
        if(pas != null){
            cont += cantidadNodos(pas.getIzq());
            cont++;
            cont += cantidadNodos(pas.getDer());
        }

        return cont;
    }


    //Funcion para comprobar si el pasajero existe, devuelve los datos
    //Preguntar si lo hago con toString
    private String pasajeroExiste(String cedula, NodoPasajero pas){
        String vacio;
        if(pas == null){
            return vacio = ""; //consultar si estos esta bien
        }else{
            if(pas.getPasajero().getCedula().compareTo(cedula) == 0)
                return pas.getPasajero().getCedula() + ";" + pas.getPasajero().getNombre() + ";"
                        + pas.getPasajero().getTelefono() + ";" ; //Falta Categoria
            else if (pas.getPasajero().getCedula().compareTo(cedula) > 0) {
                return pasajeroExiste(cedula, pas.getIzq());
            } else
                return pasajeroExiste(cedula, pas.getDer());
        }

    }

}
