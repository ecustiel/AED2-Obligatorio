package dominio;

import java.util.Objects;

public class ListaEnlazada<T> {

    private  class Nodo {
        private T dato;
        private  Nodo sig;

        public Nodo(T dato, Nodo sig) {
            this.dato = dato;
            this.sig = sig;
        }
    }
    private  Nodo inicio;


    public void agregarInicio(T dato){
        this.inicio=new Nodo(dato,inicio);
    }

    public  void listar(){
        Nodo n =inicio;
        while (n!=null){
            System.out.printf("%s,",n.dato);
            n=n.sig;


        }
        System.out.println();
    }

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
