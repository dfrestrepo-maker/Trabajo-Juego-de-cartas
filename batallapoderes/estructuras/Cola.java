package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;

public class Cola {
    private NodoCola frente;
    private NodoCola finalCola;
    private int tamaño;
    
    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamaño = 0;
    }
    
    public void encolar(Carta carta) {
        NodoCola nuevo = new NodoCola(carta);
        
        if (estaVacia()) {
            frente = nuevo;
            finalCola = nuevo;
        } else {
            finalCola.setSiguiente(nuevo);
            finalCola = nuevo;
        }
        tamaño++;
    }
    
    public Carta desencolar() {
        if (estaVacia()) {
            return null;
        }
        
        Carta carta = frente.getDato();
        frente = frente.getSiguiente();
        
        if (frente == null) {
            finalCola = null;
        }
        
        tamaño--;
        return carta;
    }
    
    public Carta verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }
    
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("  [Vacía]");
            return;
        }
        
        NodoCola actual = frente;
        int contador = 1;
        
        while (actual != null) {
            System.out.println("  " + contador + ". " + actual.getDato());
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

