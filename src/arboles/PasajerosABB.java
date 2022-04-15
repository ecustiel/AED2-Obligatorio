package arboles;

//import org.jetbrains.annotations.NotNull;

import uy.edu.ort.aed2.obligatorio.Sistema;

public class PasajerosABB<T extends Comparable<T>> {

    //Data
    private NodoPasajero<T> raiz;
    public int contadorNodos = 0;
    private Sistema.Categoria cat;

    //Getter
    public NodoPasajero<T> getRaiz() {
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
    public T insertarPasajero(T cedula, T nombre, T telefono, T categoria) {

        if(cedula == null || nombre == null || telefono ==  null || categoria == null || cedula == "" ||
        nombre == "" || telefono == "" || categoria == "") {
            return (T) "Error1";
        }else if (!validateCi((String)cedula)) {
            return (T) "Error2";
        }else if (buscarPasajero(cedula).toString().compareTo("No Existe") != 0){
            return (T)"Error3";
        }else if(this.raiz==null){
            this.raiz = new NodoPasajero<T>(cedula, nombre, telefono, categoria);
            return (T) "Insertado Correctamente Nuevo";
        }else{
            insertarPasajero(cedula, nombre, telefono, categoria, this.raiz);
            return (T) "Insertado Correctamente Nodo";
        }
    }
    //Metodo Privado
    private NodoPasajero insertarPasajero(T cedula, T nombre, T telefono, T categoria, NodoPasajero pasajero){
        NodoPasajero<T> auxiliar = new NodoPasajero<>(cedula, nombre, telefono, categoria);
        int cedulaConvertida = Integer.parseInt(cleanCi((String)cedula));
        if(pasajero == null){
            pasajero = auxiliar;
        }else if(cedulaConvertida < Integer.parseInt(cleanCi(pasajero.getPasajero().getCedula().toString()))){
            pasajero.setIzq(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getIzq()));
        }else{
            pasajero.setDer(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getDer()));
        }

        return pasajero;
    }


    //Comprobar Si Existe y devolverlo, hacemos uso de la funcion pasajeroExiste
    public T buscarPasajero(T cedula){
        T retorno = null;
        if(validateCi((String) cedula)){
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
    //Tratar de optimizar, mucho texto
    private T pasajeroExiste(T cedula, NodoPasajero pas){
        T valorRetorno;
        String cedulaNueva = (String)cedula;

        if(pas == null){
            return (T) "No Existe"; //consultar si estos esta bien
        }else{
            this.contadorNodos++;
            String cedulaExistente = pas.getPasajero().getCedula().toString();
            if(cedulaExistente.compareTo(cedulaNueva) == 0)
                 valorRetorno = (T) (pas.getPasajero().getCedula() + ";" + pas.getPasajero().getNombre() + ";"
                                         + pas.getPasajero().getTelefono() + ";" + pas.getPasajero().getCategoria());
            else if (cedulaExistente.compareTo(cedulaNueva) < 0) {
                 return  pasajeroExiste(cedula, pas.getDer());
            } else
                 return pasajeroExiste(cedula, pas.getIzq());
        }
        return valorRetorno;
    }


}
