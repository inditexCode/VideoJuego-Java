package com.lotr.modelo;

import javafx.scene.image.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class Personaje.
 */
public abstract class Personaje {
    
    /** The nombre. */
    protected String nombre;
    
    /** The vida. */
    protected int vida;
    
    /** The armadura. */
    protected int armadura;
    
    /** The imagen. */
    private Image imagen; 

    /**
     * Instantiates a new personaje.
     *
     * @param nombre the nombre
     * @param vida the vida
     * @param armadura the armadura
     */
    public Personaje(String nombre, int vida, int armadura) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() { return nombre; }
    
    /**
     * Gets the vida.
     *
     * @return the vida
     */
    public int getVida() { return vida; }
    
    /**
     * Gets the armadura.
     *
     * @return the armadura
     */
    public int getArmadura() { return armadura; }

    /**
     * Reducir vida.
     *
     * @param cantidad the cantidad
     */
    public void reducirVida(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }
    
    /**
     * Recibir danio.
     *
     * @param cantidad the cantidad
     * @return the int
     */
    public int recibirDanio(int cantidad) {
        int danioReal = Math.max(0, cantidad - armadura);
        reducirVida(danioReal);
        return danioReal;
    }


    /**
     * Esta vivo.
     *
     * @return true, if successful
     */
    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Gets the imagen.
     *
     * @return the imagen
     */
    
    public Image getImagen() {
        return imagen;
    }

    /**
     * Sets the imagen.
     *
     * @param imagen the new imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Atacar.
     *
     * @param enemigo the enemigo
     * @return the int
     */
    public abstract int atacar(Personaje enemigo);

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return nombre + " (Vida: " + vida + ", Armadura: " + armadura + ")";
    }
}
