package com.batallapoderes.logica;

import com.batallapoderes.modelo.*;
import com.batallapoderes.estructuras.*;
import com.batallapoderes.enums.*;
import java.util.Scanner;
import java.util.Random;

public class Partida {
    private Jugador jugador1;
    private Jugador cpu;
    private ListaEnlazada mazoComun;
    private TablaHash registroEstado;
    private int turnoActual;
    private SistemaDecisionCPU sistemaCPU;
    private Scanner scanner;
    private Batalla batalla;
    
    private Carta monstruoActivoJugador;
    private Carta monstruoActivoCPU;
    
    private static final int MAX_TURNOS = 50;
    private static final int PUNTOS_VICTORIA = 7;
    private static final int CARTAS_INICIALES = 4;
    private static final int CARTAS_POR_TURNO = 1;
    private static final int MAX_CARTAS_MANO = 7;
    
    public Partida(String nombreJugador) {
        this.jugador1 = new Jugador(nombreJugador, true);
        this.cpu = new Jugador("CPU", false);
        Baraja baraja = new Baraja();
        this.mazoComun = baraja.getBarajaCompleta();
        this.registroEstado = new TablaHash();
        this.sistemaCPU = new SistemaDecisionCPU();
        this.scanner = new Scanner(System.in);
        this.batalla = new Batalla();
        this.turnoActual = 0;
        this.monstruoActivoJugador = null;
        this.monstruoActivoCPU = null;
        
        registroEstado.insertar(jugador1.getNombre(), new EstadoJugador(jugador1.getNombre()));
        registroEstado.insertar(cpu.getNombre(), new EstadoJugador(cpu.getNombre()));
    }
    
    public ResultadoPartida iniciarJuego() {
    System.out.println("\n========== BATALLA DE PODERES ==========");
    System.out.println("Preparando la batalla...\n");
    
    repartirCartasIniciales();
    
    while (turnoActual < MAX_TURNOS) {
        turnoActual++;
        
        if (verificarSinCartas()) {
            return crearResultado();
        }
        
        if (verificarCondicionVictoria()) {
            return crearResultado();
        }
        
        jugarTurno();
        
        if (verificarSinCartas()) {
            return crearResultado();
        }
        
        if (verificarCondicionVictoria()) {
            return crearResultado();
        }
        
        if (turnoActual < MAX_TURNOS) {
            System.out.println("\n--- Fase de robo ---");
            int cartasRobadas1 = repartirCartasDelMazo(jugador1, CARTAS_POR_TURNO);
            int cartasRobadas2 = repartirCartasDelMazo(cpu, CARTAS_POR_TURNO);
            
            if (cartasRobadas1 > 0) {
                System.out.println("¡Robaste " + cartasRobadas1 + " carta(s)!");
            } else if (jugador1.getManoActual().size() >= MAX_CARTAS_MANO) {
                System.out.println("Ya tienes el máximo de cartas (" + MAX_CARTAS_MANO + ").");
            }
            
            if (cartasRobadas2 > 0) {
                System.out.println("CPU robó " + cartasRobadas2 + " carta(s).");
            } else if (cpu.getManoActual().size() >= MAX_CARTAS_MANO) {
                System.out.println("CPU ya tiene el máximo de cartas.");
            }
        }
    }
    
    return crearResultado();
}

private boolean verificarSinCartas() {
    boolean jugador1SinCartas = jugador1.getManoActual().estaVacia() && 
                                 (monstruoActivoJugador == null || monstruoActivoJugador.estaDerrotado());
    
    boolean cpuSinCartas = cpu.getManoActual().estaVacia() && 
                           (monstruoActivoCPU == null || monstruoActivoCPU.estaDerrotado());
    
    if (jugador1SinCartas || cpuSinCartas) {
        System.out.println("\n========================================");
        if (jugador1SinCartas && cpuSinCartas) {
            System.out.println("¡Ambos jugadores se quedaron sin cartas!");
        } else if (jugador1SinCartas) {
            System.out.println("¡Te quedaste sin cartas para jugar!");
        } else {
            System.out.println("¡CPU se quedó sin cartas para jugar!");
        }
        System.out.println("La partida termina por falta de cartas.");
        System.out.println("========================================\n");
        return true;
    }
    
    return false;
}



    
    private void repartirCartasIniciales() {
        Random random = new Random();
        
        for (int i = 0; i < CARTAS_INICIALES * 2; i++) {
            if (mazoComun.size() > 0) {
                int indice = random.nextInt(mazoComun.size());
                Carta carta = mazoComun.eliminarEnPosicion(indice);
                
                if (i % 2 == 0) {
                    jugador1.getManoActual().encolar(carta);
                } else {
                    cpu.getManoActual().encolar(carta);
                }
            }
        }
        
        System.out.println("Se repartieron " + CARTAS_INICIALES + " cartas a cada jugador.");
    }
    
    private int repartirCartasDelMazo(Jugador jugador, int cantidad) {
        int cartasRepartidas = 0;
        Random random = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            if (jugador.getManoActual().size() >= MAX_CARTAS_MANO) {
                break;
            }
            
            if (mazoComun.size() > 0) {
                int indice = random.nextInt(mazoComun.size());
                Carta carta = mazoComun.eliminarEnPosicion(indice);
                jugador.getManoActual().encolar(carta);
                cartasRepartidas++;
            } else {
                break;
            }
        }
        
        return cartasRepartidas;
    }
    
    private void jugarTurno() {
        System.out.println("\n========== TURNO " + turnoActual + " / " + MAX_TURNOS + " ==========");
        mostrarEstadoActual();
        
        Carta cartaJugador = elegirCartaJugador();
        
        if (cartaJugador == null) {
            System.out.println("\nNo jugaste ninguna carta este turno.");
            return;
        }
        
        Carta cartaCPU = elegirCartaCPU();
        
        if (cartaCPU == null) {
            System.out.println("\nCPU no tiene cartas para jugar.");
            return;
        }
        
        System.out.println("\n" + jugador1.getNombre() + " juega: " + cartaJugador);
        System.out.println("CPU juega: " + cartaCPU);
        
        procesarJugada(cartaJugador, cartaCPU);
        
        registrarEstadisticas(cartaJugador, cartaCPU);
    }
    
    private Carta elegirCartaCPU() {
        if (monstruoActivoCPU != null && !monstruoActivoCPU.estaDerrotado()) {
            Random random = new Random();
            if (random.nextInt(100) < 40) {
                return monstruoActivoCPU;
            }
        }
        
        if (cpu.getManoActual().estaVacia()) {
            if (monstruoActivoCPU != null && !monstruoActivoCPU.estaDerrotado()) {
                return monstruoActivoCPU;
            }
            return null;
        }
        
        Carta cartaSeleccionada = sistemaCPU.elegirCarta(null, cpu.getManoActual());
        return cartaSeleccionada;
    }
    
    private Carta elegirCartaJugador() {
        if (jugador1.getManoActual().estaVacia() && monstruoActivoJugador == null) {
            System.out.println("\n¡No tienes cartas en la mano ni monstruo activo!");
            return null;
        }
        
        if (monstruoActivoJugador != null && !monstruoActivoJugador.estaDerrotado()) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Continuar con tu monstruo activo: " + monstruoActivoJugador.getNombre());
            System.out.println("2. Jugar una nueva carta de tu mano");
            
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            if (opcion == 1) {
                System.out.println("Continúas con: " + monstruoActivoJugador);
                return monstruoActivoJugador;
            }
        }
        
        if (jugador1.getManoActual().estaVacia()) {
            System.out.println("\n¡No tienes cartas en la mano!");
            if (monstruoActivoJugador != null && !monstruoActivoJugador.estaDerrotado()) {
                System.out.println("Se usará tu monstruo activo.");
                return monstruoActivoJugador;
            }
            return null;
        }
        
        System.out.println("\n--- Tus cartas en mano ---");
        Cola temporal = new Cola();
        int contador = 1;
        
        while (!jugador1.getManoActual().estaVacia()) {
            Carta carta = jugador1.getManoActual().desencolar();
            System.out.println("  " + contador + ". " + carta);
            temporal.encolar(carta);
            contador++;
        }
        
        int totalCartas = contador - 1;
        
        while (!temporal.estaVacia()) {
            jugador1.getManoActual().encolar(temporal.desencolar());
        }
        
        System.out.print("\nElige el número de carta a jugar (1-" + totalCartas + "): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        if (opcion < 1 || opcion > totalCartas) {
            System.out.println("Opción inválida. Debes elegir entre 1 y " + totalCartas + ".");
            System.out.println("No se jugará ninguna carta este turno.");
            return null;
        }
        
        Carta seleccionada = null;
        contador = 1;
        
        while (!jugador1.getManoActual().estaVacia()) {
            Carta actual = jugador1.getManoActual().desencolar();
            if (contador == opcion) {
                seleccionada = actual;
            } else {
                temporal.encolar(actual);
            }
            contador++;
        }
        
        while (!temporal.estaVacia()) {
            jugador1.getManoActual().encolar(temporal.desencolar());
        }
        
        return seleccionada;
    }
    
    private void procesarJugada(Carta cartaJugador, Carta cartaCPU) {
        verificarPartesGato(cartaJugador, jugador1);
        verificarPartesGato(cartaCPU, cpu);
        
        boolean jugadorJuegoNuevaCarta = (cartaJugador != monstruoActivoJugador);
        boolean cpuJuegoNuevaCarta = (cartaCPU != monstruoActivoCPU);
        
        if (jugadorJuegoNuevaCarta) {
            if (cartaJugador.getTipoCarta() == TipoCarta.CURACION) {
                procesarCuraciónJugador(cartaJugador);
            } else if (cartaJugador.getTipoCarta() == TipoCarta.MONSTRUO) {
                monstruoActivoJugador = cartaJugador;
                System.out.println("\n" + jugador1.getNombre() + " invoca: " + monstruoActivoJugador);
            }
        }
        
        if (cpuJuegoNuevaCarta) {
            if (cartaCPU.getTipoCarta() == TipoCarta.CURACION) {
                procesarCuraciónCPU(cartaCPU);
            } else if (cartaCPU.getTipoCarta() == TipoCarta.MONSTRUO) {
                monstruoActivoCPU = cartaCPU;
                System.out.println("CPU invoca: " + monstruoActivoCPU);
            }
        }
        
        if (monstruoActivoJugador != null && monstruoActivoCPU != null) {
            ResultadoBatalla resultado = batalla.combateMonstruos(
                monstruoActivoJugador, monstruoActivoCPU, jugador1, cpu
            );
            
            System.out.println(resultado.generarResumenDetallado(monstruoActivoJugador, monstruoActivoCPU));
            
            EstadoJugador estadoJ1 = registroEstado.obtener(jugador1.getNombre());
            EstadoJugador estadoCPU = registroEstado.obtener(cpu.getNombre());
            
            if (estadoJ1 != null) {
                estadoJ1.registrarDañoInfligido(resultado.getDañoADefensor());
            }
            if (estadoCPU != null) {
                estadoCPU.registrarDañoInfligido(resultado.getDañoAAtacante());
            }
            
            if (resultado.getGanador() != null) {
                EstadoJugador estadoGanador = registroEstado.obtener(resultado.getGanador().getNombre());
                if (estadoGanador != null) {
                    estadoGanador.registrarRondaGanada();
                    estadoGanador.registrarMonstruoDerrotado();
                }
            }
            
            if (monstruoActivoJugador.estaDerrotado()) {
                System.out.println("¡Tu monstruo ha sido derrotado!");
                monstruoActivoJugador = null;
            }
            if (monstruoActivoCPU.estaDerrotado()) {
                System.out.println("¡El monstruo del CPU ha sido derrotado!");
                monstruoActivoCPU = null;
            }
        }
    }
    
    private void procesarCuraciónJugador(Carta cartaCuración) {
        System.out.println("\n¡Jugaste una carta de curación!");
        
        if (monstruoActivoJugador == null) {
            System.out.println("No tienes un monstruo en el campo para curar.");
            return;
        }
        
        if (monstruoActivoJugador.getVidaActual() >= monstruoActivoJugador.getVidaMaxima()) {
            System.out.println("Tu monstruo ya tiene vida completa.");
            return;
        }
        
        int vidaAntes = monstruoActivoJugador.getVidaActual();
        
        if (!batalla.aplicarCuración(cartaCuración, monstruoActivoJugador)) {
            System.out.println("No se puede aplicar esta curación a tu monstruo.");
            if (cartaCuración.getElemento() != Elemento.NINGUNO && 
                cartaCuración.getElemento() != monstruoActivoJugador.getElemento()) {
                System.out.println("Razón: Los elementos no coinciden.");
                System.out.println("Curación: " + cartaCuración.getElemento() + 
                                 " | Monstruo: " + monstruoActivoJugador.getElemento());
            }
            return;
        }
        
        int vidaCurada = monstruoActivoJugador.getVidaActual() - vidaAntes;
        
        EstadoJugador estadoJ1 = registroEstado.obtener(jugador1.getNombre());
        if (estadoJ1 != null) {
            estadoJ1.registrarCuración(vidaCurada);
        }
        
        System.out.println("¡Curación exitosa!");
        System.out.println(monstruoActivoJugador.getNombre() + " ahora tiene " + 
                          monstruoActivoJugador.getVidaActual() + "/" + 
                          monstruoActivoJugador.getVidaMaxima() + " HP");
    }
    
    private void procesarCuraciónCPU(Carta cartaCuración) {
        if (monstruoActivoCPU != null && 
            monstruoActivoCPU.getVidaActual() < monstruoActivoCPU.getVidaMaxima()) {
            
            int vidaAntes = monstruoActivoCPU.getVidaActual();
            
            if (batalla.aplicarCuración(cartaCuración, monstruoActivoCPU)) {
                int vidaCurada = monstruoActivoCPU.getVidaActual() - vidaAntes;
                
                EstadoJugador estadoCPU = registroEstado.obtener(cpu.getNombre());
                if (estadoCPU != null) {
                    estadoCPU.registrarCuración(vidaCurada);
                }
                
                System.out.println("\nCPU curó a su monstruo: " + monstruoActivoCPU.getNombre() +
                                 " (" + monstruoActivoCPU.getVidaActual() + "/" + 
                                 monstruoActivoCPU.getVidaMaxima() + " HP)");
            }
        }
    }
    
    private void verificarPartesGato(Carta carta, Jugador jugador) {
        if (carta.getTipoCarta() == TipoCarta.PARTE_GATO) {
            jugador.agregarParteGato(carta.getParteGato());
            System.out.println("\n¡" + jugador.getNombre() + " obtuvo: " + carta.getParteGato() + "!");
            System.out.println("Partes del Gato: " + jugador.cantidadPartesGato() + "/5");
        }
    }
    
    private void mostrarEstadoActual() {
        System.out.println("\n--- Estado de la partida ---");
        jugador1.mostrarEstadisticas();
        
        if (monstruoActivoJugador != null) {
            System.out.println("Monstruo activo: " + monstruoActivoJugador);
        }
        
        System.out.println();
        cpu.mostrarEstadisticas();
        
        if (monstruoActivoCPU != null) {
            System.out.println("Monstruo activo: " + monstruoActivoCPU);
        }
        
        System.out.println("\nCartas restantes en el mazo común: " + mazoComun.size());
    }
    
    private boolean verificarCondicionVictoria() {
        if (jugador1.tieneGatoCompleto() || cpu.tieneGatoCompleto()) {
            return true;
        }
        return jugador1.haGanado() || cpu.haGanado();
    }
    
    private ResultadoPartida crearResultado() {
    if (jugador1.tieneGatoCompleto()) {
        return new ResultadoPartida(jugador1, TipoVictoria.GATO_VACIO, turnoActual);
    }
    if (cpu.tieneGatoCompleto()) {
        return new ResultadoPartida(cpu, TipoVictoria.GATO_VACIO, turnoActual);
    }
    
    boolean jugador1SinCartas = jugador1.getManoActual().estaVacia() && 
                                (monstruoActivoJugador == null || monstruoActivoJugador.estaDerrotado());
    
    boolean cpuSinCartas = cpu.getManoActual().estaVacia() && 
                          (monstruoActivoCPU == null || monstruoActivoCPU.estaDerrotado());
    
    if (jugador1SinCartas || cpuSinCartas) {
        if (jugador1.getPuntosVictoria() > cpu.getPuntosVictoria()) {
            return new ResultadoPartida(jugador1, TipoVictoria.SIN_CARTAS, turnoActual);
        } else if (cpu.getPuntosVictoria() > jugador1.getPuntosVictoria()) {
            return new ResultadoPartida(cpu, TipoVictoria.SIN_CARTAS, turnoActual);
        } else {
            return new ResultadoPartida(null, TipoVictoria.EMPATE, turnoActual);
        }
    }
    
    if (jugador1.haGanado()) {
        return new ResultadoPartida(jugador1, TipoVictoria.PUNTOS, turnoActual);
    }
    if (cpu.haGanado()) {
        return new ResultadoPartida(cpu, TipoVictoria.PUNTOS, turnoActual);
    }
    
    if (jugador1.getPuntosVictoria() > cpu.getPuntosVictoria()) {
        return new ResultadoPartida(jugador1, TipoVictoria.PUNTOS, turnoActual);
    } else if (cpu.getPuntosVictoria() > jugador1.getPuntosVictoria()) {
        return new ResultadoPartida(cpu, TipoVictoria.PUNTOS, turnoActual);
    }
    
    return new ResultadoPartida(null, TipoVictoria.EMPATE, turnoActual);
}

    
    private void registrarEstadisticas(Carta cartaJugador, Carta cartaCPU) {
        EstadoJugador estadoJ1 = registroEstado.obtener(jugador1.getNombre());
        EstadoJugador estadoCPU = registroEstado.obtener(cpu.getNombre());
        
        if (estadoJ1 != null) {
            estadoJ1.registrarCartaJugada();
        }
        if (estadoCPU != null) {
            estadoCPU.registrarCartaJugada();
        }
    }
    
    public void mostrarEstadisticasFinales() {
        registroEstado.mostrarEstadisticas();
    }
}
