package com.batallapoderes.logica;

import com.batallapoderes.modelo.Carta;
import com.batallapoderes.enums.*;

public class CalculadorDaño {
    
    private static final double[][] TABLA_MULTIPLICADORES = {
        //        FUEGO  AGUA  PLANTA  AIRE
        /* FUEGO  */ {1.0,  0.75,  2.0,   1.0},
        /* AGUA   */ {2.0,  1.0,   0.75,  1.0},
        /* PLANTA */ {0.75, 2.0,   1.0,   1.0},
        /* AIRE   */ {1.0,  1.0,   1.0,   1.0}
    };
    
    public static int calcularDaño(Carta atacante, Carta defensor) {
        int poderBase = atacante.getPoder();
        double multiplicador = obtenerMultiplicador(
            atacante.getElemento(), 
            defensor.getElemento()
        );
        
        return (int) Math.round(poderBase * multiplicador);
    }
    
    private static double obtenerMultiplicador(Elemento atacante, Elemento defensor) {
        if (atacante == Elemento.NINGUNO || defensor == Elemento.NINGUNO) {
            return 1.0;
        }
        
        int indiceAtacante = atacante.ordinal();
        int indiceDefensor = defensor.ordinal();
        
        return TABLA_MULTIPLICADORES[indiceAtacante][indiceDefensor];
    }
    
    public static TipoEfectividad obtenerEfectividad(Elemento atacante, Elemento defensor) {
        double multiplicador = obtenerMultiplicador(atacante, defensor);
        
        if (multiplicador > 1.5) {
            return TipoEfectividad.SUPER_EFECTIVO;
        } else if (multiplicador < 0.9) {
            return TipoEfectividad.POCO_EFECTIVO;
        } else {
            return TipoEfectividad.NORMAL;
        }
    }
}
