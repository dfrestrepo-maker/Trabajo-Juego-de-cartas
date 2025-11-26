package com.batallapoderes.logica;

import com.batallapoderes.estructuras.*;
import com.batallapoderes.modelo.Carta;
import com.batallapoderes.enums.*;
import java.util.Random;

public class SistemaDecisionCPU {
    private ArbolBinarioDecision arbol;
    private Random random;
    
    public SistemaDecisionCPU() {
        this.arbol = new ArbolBinarioDecision();
        this.random = new Random();
    }
    
    public Carta elegirCarta(Carta cartaOponente, Cola manoActual) {
        if (manoActual.estaVacia()) {
            return null;
        }
        
        Cola monstruos = new Cola();
        Cola curaciones = new Cola();
        Cola partesGato = new Cola();
        Cola temporal = new Cola();
        
        while (!manoActual.estaVacia()) {
            Carta carta = manoActual.desencolar();
            temporal.encolar(carta);
            
            if (carta.getTipoCarta() == TipoCarta.MONSTRUO) {
                monstruos.encolar(carta);
            } else if (carta.getTipoCarta() == TipoCarta.CURACION) {
                curaciones.encolar(carta);
            } else if (carta.getTipoCarta() == TipoCarta.PARTE_GATO) {
                partesGato.encolar(carta);
            }
        }
        
        while (!temporal.estaVacia()) {
            manoActual.encolar(temporal.desencolar());
        }
        
        Carta cartaSeleccionada = null;
        
        if (!partesGato.estaVacia()) {
            cartaSeleccionada = partesGato.desencolar();
        } 
        else if (cartaOponente != null && cartaOponente.getTipoCarta() == TipoCarta.MONSTRUO) {
            cartaSeleccionada = elegirMejorMonstruoContraOponente(monstruos, cartaOponente);
        } 
        else if (!monstruos.estaVacia()) {
            cartaSeleccionada = elegirMonstruoMasFuerte(monstruos);
        } 
        else if (!curaciones.estaVacia()) {
            cartaSeleccionada = curaciones.desencolar();
        }
        
        if (cartaSeleccionada != null) {
            removerCartaDeMano(manoActual, cartaSeleccionada);
        }
        
        return cartaSeleccionada;
    }
    
    private Carta elegirMejorMonstruoContraOponente(Cola monstruos, Carta oponente) {
        if (monstruos.estaVacia()) {
            return null;
        }
        
        Carta mejorOpcion = null;
        int mejorPuntuacion = -1000;
        Cola temporal = new Cola();
        
        while (!monstruos.estaVacia()) {
            Carta actual = monstruos.desencolar();
            temporal.encolar(actual);
            
            int puntuacion = evaluarMonstruo(actual, oponente);
            
            if (puntuacion > mejorPuntuacion) {
                mejorPuntuacion = puntuacion;
                mejorOpcion = actual;
            }
        }
        
        while (!temporal.estaVacia()) {
            monstruos.encolar(temporal.desencolar());
        }
        
        return mejorOpcion;
    }
    
    private int evaluarMonstruo(Carta monstruo, Carta oponente) {
        int puntuacion = 0;
        
        puntuacion += monstruo.getPoder() * 2;
        
        puntuacion += monstruo.getVidaMaxima();
        
        int ventaja = calcularVentajaElemental(monstruo.getElemento(), oponente.getElemento());
        
        if (ventaja == 2) {
            puntuacion += 150;
        } else if (ventaja == -1) {
            puntuacion -= 100;
        } else {
            puntuacion += 20;
        }
        
        int dañoEsperado = (int)(monstruo.getPoder() * obtenerMultiplicador(monstruo.getElemento(), oponente.getElemento()));
        int dañoRecibido = (int)(oponente.getPoder() * obtenerMultiplicador(oponente.getElemento(), monstruo.getElemento()));
        
        if (dañoEsperado >= oponente.getVidaActual()) {
            puntuacion += 200;
        }
        
        if (monstruo.getVidaActual() > dañoRecibido) {
            puntuacion += 100;
        } else {
            puntuacion -= 50;
        }
        
        return puntuacion;
    }
    
    private int calcularVentajaElemental(Elemento atacante, Elemento defensor) {
        if (atacante == Elemento.FUEGO && defensor == Elemento.PLANTA) return 2;
        if (atacante == Elemento.AGUA && defensor == Elemento.FUEGO) return 2;
        if (atacante == Elemento.PLANTA && defensor == Elemento.AGUA) return 2;
        
        if (atacante == Elemento.FUEGO && defensor == Elemento.AGUA) return -1;
        if (atacante == Elemento.AGUA && defensor == Elemento.PLANTA) return -1;
        if (atacante == Elemento.PLANTA && defensor == Elemento.FUEGO) return -1;
        
        return 0;
    }
    
    private double obtenerMultiplicador(Elemento atacante, Elemento defensor) {
        if (atacante == Elemento.NINGUNO || defensor == Elemento.NINGUNO) {
            return 1.0;
        }
        
        if (atacante == Elemento.FUEGO && defensor == Elemento.PLANTA) return 2.0;
        if (atacante == Elemento.AGUA && defensor == Elemento.FUEGO) return 2.0;
        if (atacante == Elemento.PLANTA && defensor == Elemento.AGUA) return 2.0;
        
        if (atacante == Elemento.FUEGO && defensor == Elemento.AGUA) return 0.75;
        if (atacante == Elemento.AGUA && defensor == Elemento.PLANTA) return 0.75;
        if (atacante == Elemento.PLANTA && defensor == Elemento.FUEGO) return 0.75;
        
        return 1.0;
    }
    
    private Carta elegirMonstruoMasFuerte(Cola monstruos) {
        if (monstruos.estaVacia()) {
            return null;
        }
        
        Carta masFuerte = null;
        int mejorPuntuacion = -1;
        Cola temporal = new Cola();
        
        while (!monstruos.estaVacia()) {
            Carta actual = monstruos.desencolar();
            temporal.encolar(actual);
            
            int puntuacion = actual.getPoder() + (actual.getVidaMaxima() / 2);
            
            if (puntuacion > mejorPuntuacion) {
                mejorPuntuacion = puntuacion;
                masFuerte = actual;
            }
        }
        
        while (!temporal.estaVacia()) {
            monstruos.encolar(temporal.desencolar());
        }
        
        return masFuerte;
    }
    
    private void removerCartaDeMano(Cola mano, Carta cartaARemover) {
        Cola temporal = new Cola();
        boolean encontrada = false;
        
        while (!mano.estaVacia()) {
            Carta actual = mano.desencolar();
            
            if (!encontrada && actual.getNombre().equals(cartaARemover.getNombre()) && 
                actual.getTipoCarta() == cartaARemover.getTipoCarta()) {
                encontrada = true;
            } else {
                temporal.encolar(actual);
            }
        }
        
        while (!temporal.estaVacia()) {
            mano.encolar(temporal.desencolar());
        }
    }
}
