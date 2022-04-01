package arboles;

public class PasajerosABB {

    public static boolean validarCI(String cedula){
        return cedula.matches("^ [0-9]{1}[.][0-9]{3}[.][0-9]{3} $");
    }

}
