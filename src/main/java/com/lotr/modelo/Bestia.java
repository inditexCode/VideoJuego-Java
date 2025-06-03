package com.lotr.modelo;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Bestia.
 */
public abstract class Bestia extends Personaje {
    
    /** The Constant random. */
    private static final Random random = new Random();

    /**
     * Instantiates a new bestia.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Bestia(String nombre, int vida, int armadura) {
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
        int dado1 = random.nextInt(101); // 0-101 coloco porque quiero que tome de 0 hasta 100 pero se coloca hasta 101
        int dado2 = random.nextInt(101);
        int potencia = Math.max(dado1, dado2);
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
