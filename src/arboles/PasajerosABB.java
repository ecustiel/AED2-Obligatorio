package arboles;

//import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasajerosABB<T extends Comparable<T>> {

    //Data
    private NodoPasajero<T> raiz;
    public int contadorNodos = 0;
    StringBuilder enOrd = new StringBuilder(); //Para las listas ordenadas


    //Getter
    public NodoPasajero<T> getRaiz() {
        return raiz;
    }

    //Funciones para Comprobar validez de Cedula (Formato 4.549.685-2)

    Pattern pattern = Pattern.compile("^(([1-9]\\.[0-9])|([1-9]))[0-9]{2}\\.[0-9]{3}-[0-9]$");

    public String cleanCi(String cedula) {
        return cedula.replaceAll("[^0-9]", "");
    }


    //Insertar Pasajero
    //Metodo Publico
    public T insertarPasajero(T cedula, T nombre, T telefono, T categoria) {

        Matcher match = pattern.matcher((String)cedula);

        if (cedula == null || nombre == null || telefono == null || categoria == null || cedula == "" ||
                nombre == "" || telefono == "" || categoria == "") {
            return (T) "Error1";
        } else if (!match.find()) {
            return (T) "Error2";
        } else if (buscarPasajero(cedula).toString().compareTo("No Existe") != 0) {
            return (T) "Error3";
        } else if (this.raiz == null) {
            this.raiz = new NodoPasajero<T>(cedula, nombre, telefono, categoria);
            return (T) "Insertado Correctamente Nuevo";
        } else {
            insertarPasajero(cedula, nombre, telefono, categoria, this.raiz);
            return (T) "Insertado Correctamente Nodo";
        }
    }

    //Metodo Privado
    private NodoPasajero insertarPasajero(T cedula, T nombre, T telefono, T categoria, NodoPasajero pasajero) {
        NodoPasajero<T> auxiliar = new NodoPasajero<>(cedula, nombre, telefono, categoria);
        int cedulaNueva = Integer.parseInt(cleanCi((String) cedula));

        if (pasajero == null) {
            pasajero = auxiliar;
        } else if (cedulaNueva < Integer.parseInt(cleanCi(pasajero.getPasajero().getCedula().toString()))) {
            pasajero.setIzq(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getIzq()));
        } else {
            pasajero.setDer(insertarPasajero(cedula, nombre, telefono, categoria, pasajero.getDer()));
        }

        return pasajero;
    }


    //Comprobar Si Existe y devolverlo, hacemos uso de la funcion pasajeroExiste
    public T buscarPasajero(T cedula) {
        T retorno = null;
        Matcher match = pattern.matcher((String)cedula);
        if (match.find()) {
            retorno = pasajeroExiste(cedula, this.raiz);
        }
        return retorno;
    }


    //Funcion para comprobar si el pasajero existe, devuelve los datos
    //Tratar de optimizar, mucho texto
    //Revisar de sobrescribir el equals como hizo el profesor en clase para la comparacion de igual
    private T pasajeroExiste(T cedula, NodoPasajero pas) {
        T valorRetorno;
        String cedulaNueva = (String) cedula;

        if (pas == null) {
            return (T) "No Existe"; //consultar si estos esta bien
        } else {
            this.contadorNodos++;
            String cedulaExistente = pas.getPasajero().getCedula().toString();
            if (cedulaExistente.compareTo(cedulaNueva) == 0)
                valorRetorno = (T) pas.getPasajero().toString();
            else if (cedulaExistente.compareTo(cedulaNueva) < 0) {
                return pasajeroExiste(cedula, pas.getDer());
            } else
                return pasajeroExiste(cedula, pas.getIzq());
        }
        return valorRetorno;
    }


    //Contador de Nodos Total
    public int cantidadNodos(NodoPasajero pas) {
        int cont = 0;
        if (pas != null) {
            cont += cantidadNodos(pas.getIzq());
            cont++;
            cont += cantidadNodos(pas.getDer());
        }

        return cont;
    }

    //Arbol Vacio
    public boolean arbolVacio() {
        return raiz == null;
    }


    //Lista Pasajeros por Cedula Ascendente
    public String listarAscendente() {

    if(arbolVacio()) {
        return null;
    }else{
        ordenAscendentePorCedula(this.raiz);
    }
    return enOrd.toString();
    }

    private void ordenAscendentePorCedula(NodoPasajero pas) {
        if (pas != null) {
            ordenAscendentePorCedula(pas.getIzq());
            enOrd.append(pas.getPasajero().toString());
            ordenAscendentePorCedula(pas.getDer());
        }
    }


    //Lista Pasajeros por Cedula Descendente
    public String listaDescendente() {

        if(arbolVacio()) {
            return null;
        }else{
            ordenDescendentePorCedula(this.raiz);
        }
        return enOrd.toString();
    }

    private void ordenDescendentePorCedula(NodoPasajero pas) {
        if (pas != null) {
            ordenDescendentePorCedula(pas.getDer());
            enOrd.append(pas.getPasajero().toString());
            ordenDescendentePorCedula(pas.getIzq());
        }
    }

}
