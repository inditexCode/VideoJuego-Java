package com.lotr.modelo;

public class Orco extends Bestia {
    public Orco(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        return potencia; // Ya se descuenta armadura en recibirDanio()
    }
}
