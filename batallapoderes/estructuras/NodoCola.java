package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;

public class NodoCola {
    private Carta dato;
    private NodoCola siguiente;
    
    public NodoCola(Carta dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Carta getDato() {
        return dato;
    }
    
    public void setDato(Carta dato) {
        this.dato = dato;
    }
    
    public NodoCola getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}
