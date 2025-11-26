package com.batallapoderes.logica;

import com.batallapoderes.modelo.Jugador;
import com.batallapoderes.enums.TipoVictoria;

public class ResultadoPartida {
    private Jugador ganador;
    private TipoVictoria tipo;
    private int turnosFinal;
    private String resumen;
    
    public ResultadoPartida(Jugador ganador, TipoVictoria tipo, int turnosFinal) {
        this.ganador = ganador;
        this.tipo = tipo;
        this.turnosFinal = turnosFinal;
        generarResumen();
    }
    
    private void generarResumen() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append("╔══════════════════════════════════════════╗\n");
    sb.append("║                                          ║\n");
    sb.append("║           FIN DE LA PARTIDA              ║\n");
    sb.append("║                                          ║\n");
    sb.append("╚══════════════════════════════════════════╝\n\n");
    
    if (tipo == TipoVictoria.EMPATE) {
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║              EMPATE                      ║\n");
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append("Después de 50 turnos, ningún jugador logró ganar.\n");
    } else if (tipo == TipoVictoria.GATO_VACIO) {
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║    ¡GATO DEL VACÍO INVOCADO!             ║\n");
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append("\n🎉 ").append(ganador.getNombre()).append(" HA REUNIDO LAS 5 PARTES 🎉\n");
        sb.append("¡VICTORIA INSTANTÁNEA!\n");
    } else if (tipo == TipoVictoria.SIN_CARTAS) {
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║      VICTORIA POR FALTA DE CARTAS        ║\n");
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append("\n🏆 ").append(ganador.getNombre()).append(" GANA LA PARTIDA 🏆\n");
        sb.append("El oponente se quedó sin cartas para jugar.\n");
        sb.append("Puntos finales: ").append(ganador.getPuntosVictoria()).append("/7\n");
    } else {
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║           ¡VICTORIA!                     ║\n");
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append("\n🏆 ").append(ganador.getNombre()).append(" GANA LA PARTIDA 🏆\n");
        sb.append("Puntos finales: ").append(ganador.getPuntosVictoria()).append("/7\n");
    }
    
    sb.append("\nTurnos jugados: ").append(turnosFinal).append("/50\n");
    sb.append("══════════════════════════════════════════\n");
    
    this.resumen = sb.toString();
}

    
    public Jugador getGanador() {
        return ganador;
    }
    
    public TipoVictoria getTipo() {
        return tipo;
    }
    
    public int getTurnosFinal() {
        return turnosFinal;
    }
    
    public String getResumen() {
        return resumen;
    }
}
