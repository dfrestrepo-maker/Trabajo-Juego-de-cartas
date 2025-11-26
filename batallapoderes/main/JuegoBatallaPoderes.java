package com.batallapoderes.main;

import com.batallapoderes.logica.*;
import java.util.Scanner;

public class JuegoBatallaPoderes {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarBienvenida();
        
        boolean jugarDeNuevo = true;
        
        while (jugarDeNuevo) {
            ejecutarPartida();
            jugarDeNuevo = preguntarSiJugarDeNuevo();
        }
        
        System.out.println("\n¡Gracias por jugar Batalla de Poderes!");
        scanner.close();
    }
    
    private static void mostrarBienvenida() {
    System.out.println("\n");
    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║                                        ║");
    System.out.println("║      BATALLA DE PODERES                ║");
    System.out.println("║                                        ║");
    System.out.println("╚════════════════════════════════════════╝");
    System.out.println("\n");
    System.out.println("=== REGLAS DEL JUEGO ===");
    System.out.println("• Gana el primero en obtener 7 puntos");
    System.out.println("• Máximo 50 turnos por partida");
    System.out.println("• Inicias con 4 cartas y robas 1 por turno");
    System.out.println("• Máximo de cartas en mano: 7");
    System.out.println("• Cada turno enfrentas una carta contra el CPU");
    System.out.println("• Si derrotas un monstruo enemigo, ganas 1 punto");
    System.out.println("• Puedes continuar con tu monstruo activo");
    System.out.println("• Si te quedas sin cartas, el juego termina");
    System.out.println("• Gana quien tenga más puntos al quedarse sin cartas");
    System.out.println("• Reúne las 5 partes del Gato del Vacío para ganar instantáneamente");
    System.out.println("• Ventajas elementales:");
    System.out.println("  - Fuego > Planta (x2 daño)");
    System.out.println("  - Agua > Fuego (x2 daño)");
    System.out.println("  - Planta > Agua (x2 daño)");
    System.out.println("  - Aire es neutral (x1 daño)\n");
}
    
    private static void ejecutarPartida() {
        System.out.print("Ingresa tu nombre: ");
        String nombreJugador = scanner.nextLine();
        
        Partida partida = new Partida(nombreJugador);
        
        System.out.println("\n¡Que comience la batalla!\n");
        esperar(1500);
        
        ResultadoPartida resultado = partida.iniciarJuego();
        
        System.out.println(resultado.getResumen());
        
        System.out.print("\n¿Deseas ver las estadísticas detalladas? (s/n): ");
        String respuesta = scanner.nextLine().trim();
        
        if (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")) {
            partida.mostrarEstadisticasFinales();
        }
    }
    
    private static boolean preguntarSiJugarDeNuevo() {
        System.out.print("\n¿Deseas jugar otra partida? (s/n): ");
        String respuesta = scanner.nextLine().trim();
        return respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si");
    }
    
    private static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
