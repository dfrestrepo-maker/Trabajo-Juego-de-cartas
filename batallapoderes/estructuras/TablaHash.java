package com.batallapoderes.estructuras;

import com.batallapoderes.modelo.EstadoJugador;

public class TablaHash {
    private EntradaHash[] tabla;
    private int capacidad;
    private int tamaño;
    
    public TablaHash() {
        this.capacidad = 16;
        this.tabla = new EntradaHash[capacidad];
        this.tamaño = 0;
    }
    
    private int funcionHash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (hash * 31 + clave.charAt(i)) % capacidad;
        }
        return Math.abs(hash);
    }
    
    public void insertar(String clave, EstadoJugador valor) {
        int indice = funcionHash(clave);
        EntradaHash nueva = new EntradaHash(clave, valor);
        
        if (tabla[indice] == null) {
            tabla[indice] = nueva;
        } else {
            EntradaHash actual = tabla[indice];
            
            while (actual != null) {
                if (actual.getClave().equals(clave)) {
                    actual.setValor(valor);
                    return;
                }
                if (actual.getSiguiente() == null) {
                    actual.setSiguiente(nueva);
                    break;
                }
                actual = actual.getSiguiente();
            }
        }
        tamaño++;
    }
    
    public EstadoJugador obtener(String clave) {
        int indice = funcionHash(clave);
        EntradaHash actual = tabla[indice];
        
        while (actual != null) {
            if (actual.getClave().equals(clave)) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        
        return null;
    }
    
    public void actualizar(String clave, EstadoJugador nuevoValor) {
        insertar(clave, nuevoValor);
    }
    
    public boolean contiene(String clave) {
        return obtener(clave) != null;
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n========== ESTADÍSTICAS DE JUGADORES ==========");
        
        for (int i = 0; i < capacidad; i++) {
            EntradaHash actual = tabla[i];
            while (actual != null) {
                actual.getValor().mostrarEstadisticas();
                actual = actual.getSiguiente();
            }
        }
    }
    
    public int size() {
        return tamaño;
    }
}
