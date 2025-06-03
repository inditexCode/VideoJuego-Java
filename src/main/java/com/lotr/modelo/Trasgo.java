package com.lotr.modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Trasgo.
 */
public class Trasgo extends Bestia {
    
    /**
     * Instantiates a new trasgo.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Trasgo(String nombre, int vida, int armadura) {
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
        return potencia; // Sin ajuste adicional
    }
}
