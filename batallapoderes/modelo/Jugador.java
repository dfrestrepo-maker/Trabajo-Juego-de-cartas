package com.batallapoderes.modelo;

import com.batallapoderes.estructuras.*;
import com.batallapoderes.enums.ParteGato;

public class Jugador {
    private String nombre;
    private boolean esHumano;
    private int puntosVictoria;
    
    private Cola manoActual;
    private Cola mazoPersonal;
    private Pila cementerio;
    
    private boolean[] partesGato;
    
    public Jugador(String nombre, boolean esHumano) {
        this.nombre = nombre;
        this.esHumano = esHumano;
        this.puntosVictoria = 0;
        this.manoActual = new Cola();
        this.mazoPersonal = new Cola();
        this.cementerio = new Pila();
        this.partesGato = new boolean[5];
    }
    
    public void ganarPunto() {
        this.puntosVictoria++;
    }
    
    public boolean haGanado() {
        return this.puntosVictoria >= 7;
    }
    
    public void agregarParteGato(ParteGato parte) {
        this.partesGato[parte.ordinal()] = true;
    }
    
    public boolean tieneGatoCompleto() {
        for (boolean tiene : partesGato) {
            if (!tiene) return false;
        }
        return true;
    }
    
    public void robarCarta() {
        if (!mazoPersonal.estaVacia()) {
            Carta carta = mazoPersonal.desencolar();
            manoActual.encolar(carta);
        }
    }
    
    public void robarCartas(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            robarCarta();
        }
    }
    
    public void agregarCartaAMazo(Carta carta) {
        mazoPersonal.encolar(carta);
    }
    
    public void enviarACementerio(Carta carta) {
        cementerio.apilar(carta);
    }
    
    public int cantidadPartesGato() {
        int count = 0;
        for (boolean tiene : partesGato) {
            if (tiene) count++;
        }
        return count;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public boolean isEsHumano() {
        return esHumano;
    }
    
    public int getPuntosVictoria() {
        return puntosVictoria;
    }
    
    public Cola getManoActual() {
        return manoActual;
    }
    
    public Cola getMazoPersonal() {
        return mazoPersonal;
    }
    
    public Pila getCementerio() {
        return cementerio;
    }
    
    public boolean[] getPartesGato() {
        return partesGato;
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n" + nombre + " - Puntos: " + puntosVictoria + "/7");
        System.out.println("Cartas en mano: " + manoActual.size());
        System.out.println("Partes del Gato: " + cantidadPartesGato() + "/5");
        
        if (cantidadPartesGato() > 0) {
            System.out.print("Partes obtenidas: ");
            for (int i = 0; i < partesGato.length; i++) {
                if (partesGato[i]) {
                    System.out.print(ParteGato.values()[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
