package com.lotr.modelo;

public class Humano extends Heroe {
    public Humano(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        return potencia; // Sin ajustes
    }
}
