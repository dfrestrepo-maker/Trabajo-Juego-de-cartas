package com.batallapoderes.modelo;

import com.batallapoderes.enums.TipoEfectividad;

public class ResultadoBatalla {
    private Jugador ganador;
    private boolean empate;
    private Carta monstruoDerrotado;
    private String mensaje;
    
    private int dañoAAtacante;
    private int dañoADefensor;
    private TipoEfectividad efectividadAtacante;
    private TipoEfectividad efectividadDefensor;
    
    public ResultadoBatalla() {
        this.empate = false;
    }
    
    public String generarResumenDetallado(Carta carta1, Carta carta2) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== RESULTADO DEL COMBATE ==========\n");
        
        sb.append("\n").append(carta1.getNombre())
          .append(" [").append(carta1.getElemento()).append("]\n");
        sb.append("  Inflige: ").append(dañoADefensor).append(" de daño\n");
        sb.append("  ").append(efectividadAtacante.getMensaje()).append("\n");
        
        sb.append("\n").append(carta2.getNombre())
          .append(" [").append(carta2.getElemento()).append("]\n");
        sb.append("  Inflige: ").append(dañoAAtacante).append(" de daño\n");
        sb.append("  ").append(efectividadDefensor.getMensaje()).append("\n");
        
        sb.append("\n-------------------------------------------\n");
        sb.append(mensaje).append("\n");
        sb.append("===========================================\n");
        
        return sb.toString();
    }
    
    public Jugador getGanador() {
        return ganador;
    }
    
    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
    
    public boolean isEmpate() {
        return empate;
    }
    
    public void setEmpate(boolean empate) {
        this.empate = empate;
    }
    
    public Carta getMonstruoDerrotado() {
        return monstruoDerrotado;
    }
    
    public void setMonstruoDerrotado(Carta monstruoDerrotado) {
        this.monstruoDerrotado = monstruoDerrotado;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public int getDañoAAtacante() {
        return dañoAAtacante;
    }
    
    public void setDañoAAtacante(int dañoAAtacante) {
        this.dañoAAtacante = dañoAAtacante;
    }
    
    public int getDañoADefensor() {
        return dañoADefensor;
    }
    
    public void setDañoADefensor(int dañoADefensor) {
        this.dañoADefensor = dañoADefensor;
    }
    
    public TipoEfectividad getEfectividadAtacante() {
        return efectividadAtacante;
    }
    
    public void setEfectividadAtacante(TipoEfectividad efectividadAtacante) {
        this.efectividadAtacante = efectividadAtacante;
    }
    
    public TipoEfectividad getEfectividadDefensor() {
        return efectividadDefensor;
    }
    
    public void setEfectividadDefensor(TipoEfectividad efectividadDefensor) {
        this.efectividadDefensor = efectividadDefensor;
    }
}
