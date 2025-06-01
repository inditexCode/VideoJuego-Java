package com.lotr.modelo;

import javafx.scene.image.Image;

public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int armadura;
    private Image imagen; // ✅ Declarar el campo AQUÍ, fuera del constructor

    public Personaje(String nombre, int vida, int armadura) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getArmadura() { return armadura; }

    public void reducirVida(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // ✅ Getter y setter para imagen
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public abstract int atacar(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " (Vida: " + vida + ", Armadura: " + armadura + ")";
    }
}
