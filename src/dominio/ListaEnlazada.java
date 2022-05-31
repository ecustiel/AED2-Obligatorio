package dominio;

import java.util.Objects;

public class ListaEnlazada<T> {

    private  Nodo inicio;

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

    private Nodo fin;


    public void agregarInicio(T dato){
        this.inicio=new Nodo(dato,inicio);
    }

    //Arreglar, esta mal
    public boolean obtenerVuelo(T codVuelo){
        Nodo n = this.inicio;
        while (n !=null){
            if(n.dato.equals(codVuelo)){
                return true;
            }

            n=n.sig;


        }
        return false;
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

    /*public void visitar(Visitor<T> visitor){
        Nodo n =inicio;
        while (n!=null){
            visitor.visitar(n.dato);
            n=n.sig;


        }
    }*/

}
