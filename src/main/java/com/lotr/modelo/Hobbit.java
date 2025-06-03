package com.lotr.modelo;

// TODO: Auto-generated Javadoc
/**
 * The Class Hobbit.
 */
public class Hobbit extends Heroe {
    
    /**
     * Instantiates a new hobbit.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Hobbit(String nombre, int vida, int armadura) {
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
        if (enemigo instanceof Trasgo) {
            potencia -= 5;
        }
        return potencia;
    }
}
