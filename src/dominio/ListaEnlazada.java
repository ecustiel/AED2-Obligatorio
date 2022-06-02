package dominio;

import java.util.Objects;

public class ListaEnlazada<T> {



    private  Nodo inicio;
    private Nodo fin;
    public int contador = 0;



    private  class Nodo {
        private T dato;
        private  Nodo sig;

        public Nodo(T dato, Nodo sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }
    }


    public void agregarInicio(T dato){
        contador++;
        this.inicio=new Nodo(dato,inicio);
    }

    //Arreglar, esta mal
    public T obtenerVuelo(T vuelo){
        Nodo n = this.inicio;

        while (n !=null){
            if(n.dato == vuelo){
                return n.dato;
            }

            n=n.sig;


        }
        return null;
    }

    public void eliminar(T vuelo){
        Nodo n = this.inicio;
        while (n !=null){
            if(n.dato == vuelo){
                n.dato = null;
            }


            n=n.sig;


        }
    }

    public int getContador(){
        return contador;
    }

   /* public T agregarAlFinal(T elementoNuevo) {


        if(esVacia()) {
            inicio = fin = new Nodo(elementoNuevo, inicio);
        }else{
            fin = fin.sig = new Nodo(elementoNuevo, fin);

        }
    }*/

    public T eliminarInicio() {
        Objects.requireNonNull(inicio);
        T aRetornar = this.inicio.dato;
        this.inicio = inicio.sig;
        return aRetornar;
    }

    public boolean esVacia(){
        return inicio== null;
    }


    public T get(int indice) {

        Nodo list = inicio;
        int cont = 0;

        if(list == null){
            return null;
        }else {
            while (cont < indice && list.sig != null) {

                    list = list.sig;
                    cont++;
                }

            return list.dato;
        }

    }
    /*public void visitar(Visitor<T> visitor){
        Nodo n =inicio;
        while (n!=null){
            visitor.visitar(n.dato);
            n=n.sig;


        }
    }*/

}
