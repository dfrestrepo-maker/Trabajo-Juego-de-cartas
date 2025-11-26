package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.EstadoJugador;

public class EntradaHash {
    private String clave;
    private EstadoJugador valor;
    private EntradaHash siguiente;
    
    public EntradaHash(String clave, EstadoJugador valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public EstadoJugador getValor() {
        return valor;
    }
    
    public void setValor(EstadoJugador valor) {
        this.valor = valor;
    }
    
    public EntradaHash getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(EntradaHash siguiente) {
        this.siguiente = siguiente;
    }
}
