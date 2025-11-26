package com.batallapoderes.logica;

import com.batallapoderes.modelo.*;
import com.batallapoderes.enums.*;

public class Batalla {
    
    public ResultadoBatalla combateMonstruos(Carta monstruo1, Carta monstruo2, 
                                             Jugador jugador1, Jugador jugador2) {
        
        int dañoAMonstruo2 = CalculadorDaño.calcularDaño(monstruo1, monstruo2);
        int dañoAMonstruo1 = CalculadorDaño.calcularDaño(monstruo2, monstruo1);
        
        TipoEfectividad efectividad1vs2 = CalculadorDaño.obtenerEfectividad(
            monstruo1.getElemento(), monstruo2.getElemento()
        );
        TipoEfectividad efectividad2vs1 = CalculadorDaño.obtenerEfectividad(
            monstruo2.getElemento(), monstruo1.getElemento()
        );
        
        monstruo2.recibirDaño(dañoAMonstruo2);
        monstruo1.recibirDaño(dañoAMonstruo1);
        
        ResultadoBatalla resultado = new ResultadoBatalla();
        resultado.setDañoADefensor(dañoAMonstruo2);
        resultado.setDañoAAtacante(dañoAMonstruo1);
        resultado.setEfectividadAtacante(efectividad1vs2);
        resultado.setEfectividadDefensor(efectividad2vs1);
        
        boolean monstruo1Derrotado = monstruo1.estaDerrotado();
        boolean monstruo2Derrotado = monstruo2.estaDerrotado();
        
        if (monstruo1Derrotado && monstruo2Derrotado) {
            resultado.setEmpate(true);
            resultado.setMensaje("¡Ambos monstruos fueron derrotados!");
            jugador1.enviarACementerio(monstruo1);
            jugador2.enviarACementerio(monstruo2);
        } 
        else if (monstruo2Derrotado) {
            jugador1.ganarPunto();
            resultado.setGanador(jugador1);
            resultado.setMonstruoDerrotado(monstruo2);
            resultado.setMensaje(jugador1.getNombre() + " gana la ronda y obtiene 1 punto!");
            jugador2.enviarACementerio(monstruo2);
        } 
        else if (monstruo1Derrotado) {
            jugador2.ganarPunto();
            resultado.setGanador(jugador2);
            resultado.setMonstruoDerrotado(monstruo1);
            resultado.setMensaje(jugador2.getNombre() + " gana la ronda y obtiene 1 punto!");
            jugador1.enviarACementerio(monstruo1);
        } 
        else {
            resultado.setMensaje("Ambos monstruos sobreviven al combate");
        }
        
        return resultado;
    }
    
    public boolean aplicarCuración(Carta cartaCuración, Carta monstruoObjetivo) {
        if (monstruoObjetivo.getTipoCarta() != TipoCarta.MONSTRUO) {
            return false;
        }
        
        if (!puedeSerCurado(cartaCuración, monstruoObjetivo)) {
            return false;
        }
        
        if (monstruoObjetivo.getVidaActual() >= monstruoObjetivo.getVidaMaxima()) {
            return false;
        }
        
        monstruoObjetivo.curar(cartaCuración.getPuntosCuración());
        return true;
    }
    
    private boolean puedeSerCurado(Carta cartaCuración, Carta monstruo) {
        if (cartaCuración.getElemento() == Elemento.NINGUNO) {
            return true;
        }
        
        return cartaCuración.getElemento() == monstruo.getElemento();
    }
}
