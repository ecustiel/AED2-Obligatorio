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


    //Funcion para Comprobar validez de Cedula (Formato 4.549.685) sin verificador
    public static boolean comprobarCi(String cedula){

        Pattern patron = Pattern.compile("^[0-9][.][0-9]{3}[.][0-9]{3}$");
        Matcher matcher = patron.matcher(cedula);

        return matcher.matches();
    }


    //Insertar Pasajero
    //public String insertarPasajero(String cedula, String nombre, String telefrono, Categoria categoria) {

    //}


    //Comprobar Si Existe y devolverlo, hacemos uso de la funcion pasajeroExiste
    public String buscarPasajero(String cedula){

        if(comprobarCi(cedula)){
            return pasajeroExiste(cedula, this.pasajeros);
        }else{
            return "No Existe";
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
    private String pasajeroExiste(String cedula, NodoPasajero pas){
        String vacio;
        if(pas == null){
            return vacio = "";
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
