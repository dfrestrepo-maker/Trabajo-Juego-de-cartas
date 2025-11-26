package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;

public class NodoPila {
    private Carta dato;
    private NodoPila siguiente;
    
    public NodoPila(Carta dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Carta getDato() {
        return dato;
    }
    
    public void setDato(Carta dato) {
        this.dato = dato;
    }
    
    public NodoPila getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }
}
