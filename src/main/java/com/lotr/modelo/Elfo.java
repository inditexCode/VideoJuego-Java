package com.lotr.modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Elfo.
 */
public class Elfo extends Heroe {
    
    /**
     * Instantiates a new elfo.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Elfo(String nombre, int vida, int armadura) {
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
        if (enemigo instanceof Orco) {
            potencia += 10;
        }
        return potencia;
    }
}
