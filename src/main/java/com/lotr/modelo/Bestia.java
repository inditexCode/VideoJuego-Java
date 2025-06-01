package com.lotr.modelo;

import java.util.Random;

public abstract class Bestia extends Personaje {
    private static final Random random = new Random();

    public Bestia(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public int atacar(Personaje enemigo) {
        int potencia = random.nextInt(91); // 0-90
        return ajustarAtaque(potencia, enemigo);
    }

    protected abstract int ajustarAtaque(int potencia, Personaje enemigo);
}
