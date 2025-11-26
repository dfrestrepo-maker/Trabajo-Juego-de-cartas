package com.batallapoderes.modelo;

public class EstadoJugador {
    private String nombreJugador;
    private int cartasJugadas;
    private int monstruosDerrotados;
    private int poderTotalInfligido;
    private int rondasGanadas;
    private int curacionesTotales;
    
    public EstadoJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.cartasJugadas = 0;
        this.monstruosDerrotados = 0;
        this.poderTotalInfligido = 0;
        this.rondasGanadas = 0;
        this.curacionesTotales = 0;
    }
    
    public void registrarCartaJugada() {
        this.cartasJugadas++;
    }
    
    public void registrarMonstruoDerrotado() {
        this.monstruosDerrotados++;
    }
    
    public void registrarDañoInfligido(int daño) {
        this.poderTotalInfligido += daño;
    }
    
    public void registrarRondaGanada() {
        this.rondasGanadas++;
    }
    
    public void registrarCuración(int puntos) {
        this.curacionesTotales += puntos;
    }
    
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    public int getCartasJugadas() {
        return cartasJugadas;
    }
    
    public int getMonstruosDerrotados() {
        return monstruosDerrotados;
    }
    
    public int getPoderTotalInfligido() {
        return poderTotalInfligido;
    }
    
    public int getRondasGanadas() {
        return rondasGanadas;
    }
    
    public int getCuracionesTotales() {
        return curacionesTotales;
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║  ESTADÍSTICAS: " + formatearNombre(nombreJugador));
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  Cartas jugadas:        " + cartasJugadas);
        System.out.println("  Monstruos derrotados:  " + monstruosDerrotados);
        System.out.println("  Daño total infligido:  " + poderTotalInfligido);
        System.out.println("  Rondas ganadas:        " + rondasGanadas);
        System.out.println("  HP curados:            " + curacionesTotales);
        System.out.println("══════════════════════════════════════════");
    }
    
    private String formatearNombre(String nombre) {
        if (nombre.length() > 20) {
            return nombre.substring(0, 20) + "...";
        }
        
        int espacios = 22 - nombre.length();
        StringBuilder sb = new StringBuilder(nombre);
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");
        }
        sb.append("║");
        return sb.toString();
    }
}
