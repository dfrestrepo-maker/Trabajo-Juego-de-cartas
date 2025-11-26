package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;
import com.batallapoderes.enums.Elemento;
import java.util.Random;

public class ArbolBinarioDecision {
    private NodoArbol raiz;
    
    public ArbolBinarioDecision() {
        construirArbol();
    }
    
    private void construirArbol() {
        raiz = new NodoArbol("¿El oponente jugó monstruo?");
        
        raiz.setIzquierda(new NodoArbol("¿Tengo cartas de curación?"));
        raiz.setDerecha(new NodoArbol("¿Tengo ventaja elemental?"));
        
        raiz.getIzquierda().setIzquierda(new NodoArbol("JUGAR_MONSTRUO_FUERTE"));
        raiz.getIzquierda().setDerecha(new NodoArbol("CONSIDERAR_CURACION"));
        
        raiz.getDerecha().setIzquierda(new NodoArbol("JUGAR_MONSTRUO_EQUILIBRADO"));
        raiz.getDerecha().setDerecha(new NodoArbol("JUGAR_CON_VENTAJA"));
    }
    
    public Carta decidirCarta(Carta cartaOponente, Cola mazoCPU, boolean tieneCuracion) {
        if (mazoCPU.estaVacia()) {
            return null;
        }
        
        String estrategia = navegarArbol(cartaOponente, tieneCuracion);
        return seleccionarCartaSegunEstrategia(estrategia, cartaOponente, mazoCPU);
    }
    
    private String navegarArbol(Carta cartaOponente, boolean tieneCuracion) {
        NodoArbol actual = raiz;
        
        while (!actual.esHoja()) {
            if (actual.getDecision().contains("oponente jugó monstruo")) {
                actual = cartaOponente != null ? actual.getDerecha() : actual.getIzquierda();
            } else if (actual.getDecision().contains("cartas de curación")) {
                actual = tieneCuracion ? actual.getDerecha() : actual.getIzquierda();
            } else if (actual.getDecision().contains("ventaja elemental")) {
                boolean tieneVentaja = cartaOponente != null && 
                                      evaluarVentajaDisponible(cartaOponente.getElemento());
                actual = tieneVentaja ? actual.getDerecha() : actual.getIzquierda();
            } else {
                break;
            }
        }
        
        return actual.getDecision() != null ? actual.getDecision() : "JUGAR_ALEATORIO";
    }
    
    private boolean evaluarVentajaDisponible(Elemento elementoOponente) {
        Random random = new Random();
        return random.nextBoolean();
    }
    
    private Carta seleccionarCartaSegunEstrategia(String estrategia, Carta cartaOponente, Cola mazoCPU) {
        if (mazoCPU.estaVacia()) return null;
        
        return mazoCPU.desencolar();
    }
}
