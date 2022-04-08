package arboles;

//import org.jetbrains.annotations.NotNull;

import dominio.Pasajero;
import uy.edu.ort.aed2.obligatorio.Sistema;

public class PasajerosABB {

    //Data
    private NodoPasajero raiz;
    private Sistema.Categoria cat;

    //Getter
    public NodoPasajero getRaiz() {
        return raiz;
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
    //Metodo Publico
    public String insertarPasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {

        if(cedula == null || nombre == null || telefono ==  null || categoria == null) {
            return "Error1";

        }else if (!validateCi(cedula)) {
            return "Error2";
       /* }*//*else if (!buscarPasajero(cedula).equals("No Existe") ){
            return "Error3";*/
        }else if(this.raiz==null){
            this.raiz = new NodoPasajero(nombre, cedula, telefono, categoria);
            return "Insertado Correctamente Nuevo";
        }else{
            insertarPasajero(cedula, nombre, telefono, categoria, this.raiz);
            return "Insertado Correctamente en Nodo";
        }
    }

    //Metodo Privado
    private NodoPasajero insertarPasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria, NodoPasajero pasajero){
        NodoPasajero auxiliar = new NodoPasajero(cedula, nombre, telefono, categoria);
        int cedulaConvertida = Integer.parseInt(cedula);
        int cedulaAuxConvertida = Integer.parseInt(auxiliar.getPasajero().getCedula());
        if(pasajero == null){
            pasajero = auxiliar;
        }else if(cedulaConvertida < cedulaAuxConvertida){
            pasajero.setIzq(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getIzq()));
        }else{
            pasajero.setDer(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getDer()));
        }

        return pasajero;
    }


    //Comprobar Si Existe y devolverlo, hacemos uso de la funcion pasajeroExiste
    public String buscarPasajero(String cedula){
        String retorno = null;
        if(validateCi(cedula)){
             retorno = pasajeroExiste(cedula, this.raiz);
        }
    return retorno;
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
        String valorRetorno;
        if(pas == null){
            return "No Existe"; //consultar si estos esta bien
        }else{
            if(pas.getIzq().getPasajero().getCedula().compareTo(cedula) == 0)
                 valorRetorno =  pas.getPasajero().getCedula() + ";" + pas.getPasajero().getNombre() + ";"
                        + pas.getPasajero().getTelefono() + ";" ; //Falta Categoria
            else if (pas.getPasajero().getCedula().compareTo(cedula) > 0) {
                 valorRetorno =  pasajeroExiste(cedula, pas.getIzq());
            } else
                 valorRetorno = pasajeroExiste(cedula, pas.getDer());
        }
        return valorRetorno;
    }

}
