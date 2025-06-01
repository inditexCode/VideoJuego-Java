package com.lotr.modelo;

import java.util.Random;

public abstract class Heroe extends Personaje {
    private static final Random random = new Random();

    public Heroe(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    public int atacar(Personaje enemigo) {
        int dado1 = random.nextInt(101); // 0-100
        int dado2 = random.nextInt(101); // 0-100
        int potencia = Math.max(dado1, dado2);

        // Ajustes especiales por tipo de héroe (en subclases)
        return ajustarAtaque(potencia, enemigo);
    }

    protected abstract int ajustarAtaque(int potencia, Personaje enemigo);
}
