package arboles;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasajerosABB {

    private NodoPasajero pasajero;


    //Funcion para Comprobar validez de Cedula (Formato 4.549.685) sin verificador
    public static boolean comprobarCi(String cedula){

        Pattern patron = Pattern.compile("^[0-9][.][0-9]{3}[.][0-9]{3}$");
        Matcher matcher = patron.matcher(cedula);

        return matcher.matches();
    }

}
