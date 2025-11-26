package com.batallapoderes.modelo;

import com.batallapoderes.enums.*;

public class Carta {
    private String nombre;
    private TipoCarta tipoCarta;
    private Elemento elemento;
    
    private int poder;
    private int vidaActual;
    private int vidaMaxima;
    
    private int puntosCuración;
    
    private ParteGato parteGato;
    
    public Carta(String nombre, TipoCarta tipoCarta, Elemento elemento) {
        this.nombre = nombre;
        this.tipoCarta = tipoCarta;
        this.elemento = elemento;
    }
    
    public static Carta crearMonstruo(String nombre, Elemento elemento, int poder, int vidaMaxima) {
        Carta carta = new Carta(nombre, TipoCarta.MONSTRUO, elemento);
        carta.poder = poder;
        carta.vidaMaxima = vidaMaxima;
        carta.vidaActual = vidaMaxima;
        return carta;
    }
    
    public static Carta crearCuración(String nombre, Elemento elemento, int puntosCuración) {
        Carta carta = new Carta(nombre, TipoCarta.CURACION, elemento);
        carta.puntosCuración = puntosCuración;
        return carta;
    }
    
    public static Carta crearParteGato(ParteGato parte) {
        Carta carta = new Carta("Gato del Vacío - " + parte, TipoCarta.PARTE_GATO, Elemento.NINGUNO);
        carta.parteGato = parte;
        return carta;
    }
    
    public void recibirDaño(int daño) {
        this.vidaActual = Math.max(this.vidaActual - daño, 0);
    }
    
    public void curar(int puntos) {
        this.vidaActual = Math.min(this.vidaActual + puntos, this.vidaMaxima);
    }
    
    public boolean estaDerrotado() {
        return this.vidaActual <= 0;
    }
    
    public void restaurarVidaCompleta() {
        this.vidaActual = this.vidaMaxima;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public TipoCarta getTipoCarta() {
        return tipoCarta;
    }
    
    public Elemento getElemento() {
        return elemento;
    }
    
    public int getPoder() {
        return poder;
    }
    
    public int getVidaActual() {
        return vidaActual;
    }
    
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    
    public int getPuntosCuración() {
        return puntosCuración;
    }
    
    public ParteGato getParteGato() {
        return parteGato;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre);
        
        if (tipoCarta == TipoCarta.MONSTRUO) {
            sb.append(" [").append(elemento).append("]");
            sb.append(" | POD: ").append(poder);
            sb.append(" | HP: ").append(vidaActual).append("/").append(vidaMaxima);
        } else if (tipoCarta == TipoCarta.CURACION) {
            String elementoStr = elemento == Elemento.NINGUNO ? "UNIVERSAL" : elemento.toString();
            sb.append(" [").append(elementoStr).append("]");
            sb.append(" | Cura: +").append(puntosCuración).append(" HP");
        } else {
            sb.append(" [PARTE DEL GATO]");
        }
        
        return sb.toString();
    }
}
