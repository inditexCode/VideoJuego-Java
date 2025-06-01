package com.lotr.modelo;

public class Orco extends Bestia {
    public Orco(String nombre, int vida, int armadura) {
        super(nombre, vida, armadura);
    }

    @Override
    protected int ajustarAtaque(int potencia, Personaje enemigo) {
        // Reduce la armadura del oponente un 10% (solo este turno)
        int armaduraReducida = (int) (enemigo.getArmadura() * 0.9);
        int dano = potencia - armaduraReducida;
        return Math.max(dano, 0);
    }
}
