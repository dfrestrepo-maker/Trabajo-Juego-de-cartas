package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;

public class NodoArbol {
    private String decision;
    private NodoArbol izquierda;
    private NodoArbol derecha;
    private Carta cartaSugerida;
    
    public NodoArbol(String decision) {
        this.decision = decision;
        this.izquierda = null;
        this.derecha = null;
        this.cartaSugerida = null;
    }
    
    public NodoArbol(Carta cartaSugerida) {
        this.decision = null;
        this.cartaSugerida = cartaSugerida;
        this.izquierda = null;
        this.derecha = null;
    }
    
    public boolean esHoja() {
        return cartaSugerida != null;
    }
    
    public String getDecision() {
        return decision;
    }
    
    public void setDecision(String decision) {
        this.decision = decision;
    }
    
    public NodoArbol getIzquierda() {
        return izquierda;
    }
    
    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }
    
    public NodoArbol getDerecha() {
        return derecha;
    }
    
    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }
    
    public Carta getCartaSugerida() {
        return cartaSugerida;
    }
    
    public void setCartaSugerida(Carta cartaSugerida) {
        this.cartaSugerida = cartaSugerida;
    }
}
