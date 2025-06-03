package com.lotr.modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Humano.
 */
public class Humano extends Heroe {
    
    /**
     * Instantiates a new humano.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Humano(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    /**
     * Ajustar ataque.
     *
     * @param potencia the potencia
     * @param enemigo the enemigo
     * @return the int
     */
    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        return potencia; // Sin ajustes
    }
}
