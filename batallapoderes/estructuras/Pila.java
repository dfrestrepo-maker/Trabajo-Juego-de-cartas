package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.Carta;

public class Pila {
    private NodoPila tope;
    private int tamaño;
    
    public Pila() {
        this.tope = null;
        this.tamaño = 0;
    }
    
    public void apilar(Carta carta) {
        NodoPila nuevo = new NodoPila(carta);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamaño++;
    }
    
    public Carta desapilar() {
        if (estaVacia()) {
            return null;
        }
        
        Carta carta = tope.getDato();
        tope = tope.getSiguiente();
        tamaño--;
        return carta;
    }
    
    public Carta verTope() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }
    
    public void mostrarHistorial() {
        if (estaVacia()) {
            System.out.println("  [No hay cartas derrotadas]");
            return;
        }
        
        System.out.println("\n  Cartas derrotadas (más reciente primero):");
        NodoPila actual = tope;
        int contador = 1;
        
        while (actual != null && contador <= 10) {
            System.out.println("  " + contador + ". " + actual.getDato().getNombre());
            actual = actual.getSiguiente();
            contador++;
        }
        
        if (tamaño > 10) {
            System.out.println("  ... y " + (tamaño - 10) + " más");
        }
    }
    
    public boolean estaVacia() {
        return tamaño == 0;
    }
    
    public int size() {
        return tamaño;
    }
}
