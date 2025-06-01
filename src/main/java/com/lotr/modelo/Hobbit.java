package com.lotr.modelo;

public class Hobbit extends Heroe {
    public Hobbit(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        if (enemigo instanceof Trasgo) {
            potencia -= 5;
        }
        return potencia;
    }
}
