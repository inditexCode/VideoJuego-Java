package com.lotr.modelo;

public class Trasgo extends Bestia {
    public Trasgo(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        int dano = potencia - enemigo.getArmadura();
        return Math.max(dano, 0);
    }
}
