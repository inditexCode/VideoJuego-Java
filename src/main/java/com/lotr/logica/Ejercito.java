package com.lotr.logica;

import com.lotr.modelo.Personaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Ejercito.
 */
public class Ejercito {

    /** The nombre. */
    private final String nombre;
    
    /** The personajes. */
    private final List<Personaje> personajes;

    /**
     * Instantiates a new ejercito.
     *
     * @param nombre the nombre
     */
    public Ejercito(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    /**
     * Agregar.
     *
     * @param p the p
     */
    public void agregar(Personaje p) {
        personajes.add(p);
    }

    /**
     * Gets the.
     *
     * @param index the index
     * @return the personaje
     */
    public Personaje get(int index) {
        return personajes.get(index);
    }

    /**
     * Tamano.
     *
     * @return the int
     */
    public int tamano() {
        return personajes.size();
    }

    /**
     * Esta vacio.
     *
     * @return true, if successful
     */
    public boolean estaVacio() {
        return personajes.isEmpty();
    }

    /**
     * Eliminar muertos.
     */
    public void eliminarMuertos() {
        personajes.removeIf(p -> !p.estaVivo());
    }

    /**
     * Gets the lista.
     *
     * @return the lista
     */
    public List<Personaje> getLista() {
        return personajes;
    }

    /**
     * Gets the personajes.
     *
     * @return the personajes
     */
    public List<Personaje> getPersonajes() {
        return personajes;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
}
