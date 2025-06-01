package com.lotr.modelo;

public class Elfo extends Heroe {
    public Elfo(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        if (enemigo instanceof Orco) {
            potencia += 10;
        }
        return potencia;
    }
}
