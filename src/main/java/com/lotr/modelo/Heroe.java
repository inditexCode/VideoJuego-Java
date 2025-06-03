package com.lotr.modelo;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroe.
 */
public abstract class Heroe extends Personaje {
    
    /** The Constant random. */
    private static final Random random = new Random();

    /**
     * Instantiates a new heroe.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Heroe(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    /**
     * Atacar.
     *
     * @param enemigo the enemigo
     * @return the int
     */
    @Override
    public int atacar(Personaje enemigo) {
        int dado1 = random.nextInt(101); // 0-100
        int dado2 = random.nextInt(101); // 0-100
        int potencia = Math.max(dado1, dado2);

        // Ajustes especiales por tipo de héroe (en subclases)
        return ajustarAtaque(potencia, enemigo);
    }

    /**
     * Ajustar ataque.
     *
     * @param potencia the potencia
     * @param enemigo the enemigo
     * @return the int
     */
    protected abstract int ajustarAtaque(int potencia, Personaje enemigo);
}
