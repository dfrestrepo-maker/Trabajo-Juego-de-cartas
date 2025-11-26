package com.batallapoderes.logica;

import com.batallapoderes.estructuras.ListaEnlazada;
import com.batallapoderes.modelo.Carta;
import com.batallapoderes.enums.*;

public class Baraja {
    private ListaEnlazada barajaCompleta;
    
    public Baraja() {
        this.barajaCompleta = new ListaEnlazada();
        inicializarBaraja();
    }
    
    private void inicializarBaraja() {
        crearMonstruos();
        crearCartasCuración();
        crearPartesGato();
    }
    
    private void crearMonstruos() {
        crearMonstruosFuego();
        crearMonstruosAgua();
        crearMonstruosPlanta();
        crearMonstruosAire();
    }
    
    private void crearMonstruosFuego() {
        barajaCompleta.agregar(Carta.crearMonstruo("Dragón Infernal", Elemento.FUEGO, 35, 90));
        barajaCompleta.agregar(Carta.crearMonstruo("Fénix Ardiente", Elemento.FUEGO, 32, 85));
        barajaCompleta.agregar(Carta.crearMonstruo("Salamandra Roja", Elemento.FUEGO, 28, 95));
        barajaCompleta.agregar(Carta.crearMonstruo("Lobo de Lava", Elemento.FUEGO, 30, 88));
        barajaCompleta.agregar(Carta.crearMonstruo("Titán Flamígero", Elemento.FUEGO, 33, 92));
        barajaCompleta.agregar(Carta.crearMonstruo("Serpiente Solar", Elemento.FUEGO, 27, 87));
        barajaCompleta.agregar(Carta.crearMonstruo("Guerrero Ígneo", Elemento.FUEGO, 29, 90));
        barajaCompleta.agregar(Carta.crearMonstruo("Espíritu de Fuego", Elemento.FUEGO, 26, 83));
    }
    
    private void crearMonstruosAgua() {
        barajaCompleta.agregar(Carta.crearMonstruo("Leviatán Marino", Elemento.AGUA, 32, 95));
        barajaCompleta.agregar(Carta.crearMonstruo("Kraken Profundo", Elemento.AGUA, 30, 92));
        barajaCompleta.agregar(Carta.crearMonstruo("Tortuga Ancestral", Elemento.AGUA, 25, 100));
        barajaCompleta.agregar(Carta.crearMonstruo("Serpiente Acuática", Elemento.AGUA, 28, 88));
        barajaCompleta.agregar(Carta.crearMonstruo("Delfín Guerrero", Elemento.AGUA, 27, 85));
        barajaCompleta.agregar(Carta.crearMonstruo("Tritón Guardián", Elemento.AGUA, 29, 90));
        barajaCompleta.agregar(Carta.crearMonstruo("Medusa Marina", Elemento.AGUA, 31, 87));
        barajaCompleta.agregar(Carta.crearMonstruo("Tiburón Abisal", Elemento.AGUA, 26, 89));
    }
    
    private void crearMonstruosPlanta() {
        barajaCompleta.agregar(Carta.crearMonstruo("Ent Ancestral", Elemento.PLANTA, 30, 105));
        barajaCompleta.agregar(Carta.crearMonstruo("Rosa Carnívora", Elemento.PLANTA, 28, 95));
        barajaCompleta.agregar(Carta.crearMonstruo("Guardián del Bosque", Elemento.PLANTA, 27, 100));
        barajaCompleta.agregar(Carta.crearMonstruo("Hiedra Venenosa", Elemento.PLANTA, 25, 92));
        barajaCompleta.agregar(Carta.crearMonstruo("Cactus Guerrero", Elemento.PLANTA, 29, 98));
        barajaCompleta.agregar(Carta.crearMonstruo("Espíritu Floral", Elemento.PLANTA, 26, 90));
        barajaCompleta.agregar(Carta.crearMonstruo("Treant Colosal", Elemento.PLANTA, 31, 103));
    }
    
    private void crearMonstruosAire() {
        barajaCompleta.agregar(Carta.crearMonstruo("Águila Celestial", Elemento.AIRE, 33, 80));
        barajaCompleta.agregar(Carta.crearMonstruo("Dragón de Viento", Elemento.AIRE, 31, 85));
        barajaCompleta.agregar(Carta.crearMonstruo("Halcón Veloz", Elemento.AIRE, 30, 75));
        barajaCompleta.agregar(Carta.crearMonstruo("Elemental de Tormenta", Elemento.AIRE, 28, 82));
        barajaCompleta.agregar(Carta.crearMonstruo("Grifo Alado", Elemento.AIRE, 29, 88));
        barajaCompleta.agregar(Carta.crearMonstruo("Espíritu del Cielo", Elemento.AIRE, 27, 78));
        barajaCompleta.agregar(Carta.crearMonstruo("Cóndor Divino", Elemento.AIRE, 32, 83));
    }
    
    private void crearCartasCuración() {
        barajaCompleta.agregar(Carta.crearCuración("Poción Universal", Elemento.NINGUNO, 30));
        barajaCompleta.agregar(Carta.crearCuración("Elixir Místico", Elemento.NINGUNO, 30));
        
        barajaCompleta.agregar(Carta.crearCuración("Llama Curativa", Elemento.FUEGO, 45));
        barajaCompleta.agregar(Carta.crearCuración("Cenizas Regeneradoras", Elemento.FUEGO, 45));
        
        barajaCompleta.agregar(Carta.crearCuración("Agua Bendita", Elemento.AGUA, 45));
        barajaCompleta.agregar(Carta.crearCuración("Marea Sanadora", Elemento.AGUA, 45));
        
        barajaCompleta.agregar(Carta.crearCuración("Néctar Natural", Elemento.PLANTA, 45));
        barajaCompleta.agregar(Carta.crearCuración("Raíz Curativa", Elemento.PLANTA, 45));
        
        barajaCompleta.agregar(Carta.crearCuración("Brisa Revitalizante", Elemento.AIRE, 45));
        barajaCompleta.agregar(Carta.crearCuración("Viento Sanador", Elemento.AIRE, 45));
    }
    
    private void crearPartesGato() {
        barajaCompleta.agregar(Carta.crearParteGato(ParteGato.CABEZA));
        barajaCompleta.agregar(Carta.crearParteGato(ParteGato.COLA));
        barajaCompleta.agregar(Carta.crearParteGato(ParteGato.BIGOTES));
        barajaCompleta.agregar(Carta.crearParteGato(ParteGato.OREJAS));
        barajaCompleta.agregar(Carta.crearParteGato(ParteGato.CUERPO));
    }
    
    public ListaEnlazada getBarajaCompleta() {
        return barajaCompleta;
    }
}
