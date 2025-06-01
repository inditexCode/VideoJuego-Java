package com.lotr.logica;

import com.lotr.modelo.Personaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ejercito {

    private final String nombre;
    private final List<Personaje> personajes;

    public Ejercito(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
    }

    public void agregar(Personaje p) {
        personajes.add(p);
    }

    public Personaje get(int index) {
        return personajes.get(index);
    }

    public int tamano() {
        return personajes.size();
    }

    public boolean estaVacio() {
        return personajes.isEmpty();
    }

    public void eliminarMuertos() {
        personajes.removeIf(p -> !p.estaVivo());
    }

    public List<Personaje> getLista() {
        return personajes;
    }

    // ✅ Método que faltaba
    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public String getNombre() {
        return nombre;
    }
}
