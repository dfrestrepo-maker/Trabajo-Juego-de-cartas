package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;
import java.util.Random;

public class ListaEnlazada {
    private Nodo cabeza;
    private int tamaño;
    
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    public void agregar(Carta carta) {
        Nodo nuevo = new Nodo(carta);
        
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }
    
    public Carta obtenerEnPosicion(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }
    
    public Carta obtenerAleatoria() {
        if (tamaño == 0) return null;
        
        Random random = new Random();
        int indice = random.nextInt(tamaño);
        return obtenerEnPosicion(indice);
    }
    
    public Carta eliminarEnPosicion(int indice) {
        if (indice < 0 || indice >= tamaño || cabeza == null) {
            return null;
        }
        
        Carta cartaEliminada;
        
        if (indice == 0) {
            cartaEliminada = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguiente();
            }
            cartaEliminada = actual.getSiguiente().getDato();
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
        
        tamaño--;
        return cartaEliminada;
    }
    
    public void recorrer() {
        Nodo actual = cabeza;
        int contador = 1;
        
        while (actual != null) {
            System.out.println(contador + ". " + actual.getDato());
            actual = actual.getSiguiente();
            contador++;
        }
    }
    
    public boolean estaVacia() {
        return tamaño == 0;
    }
    
    public int size() {
        return tamaño;
    }
}
