package com.lotr.logica;

import com.lotr.modelo.Personaje;

public class Batalla {

    private Ejercito heroes;
    private Ejercito bestias;

    public Batalla(Ejercito heroes, Ejercito bestias) {
        this.heroes = heroes;
        this.bestias = bestias;
    }

    public void iniciarBatalla() {
        int turno = 1;

        while (!heroes.estaVacio() && !bestias.estaVacio()) {
            System.out.println("Turno " + turno + ":");

            int enfrentamientos = Math.min(heroes.tamano(), bestias.tamano());

            for (int i = 0; i < enfrentamientos; i++) {
                Personaje heroe = heroes.get(i);
                Personaje bestia = bestias.get(i);

                System.out.println("  Lucha entre " + heroe + " y " + bestia);

                int ataqueHeroe = heroe.atacar(bestia);
                if (ataqueHeroe > 0) {
                    bestia.reducirVida(ataqueHeroe);
                    System.out.println("    " + heroe.getNombre() + " saca " + ataqueHeroe + " y le quita " + ataqueHeroe + " de vida a " + bestia.getNombre());
                } else {
                    System.out.println("    " + heroe.getNombre() + " saca " + ataqueHeroe + " y no hace daño a " + bestia.getNombre());
                }

                if (!bestia.estaVivo()) {
                    System.out.println("  ¡Muere " + bestia.getClass().getSimpleName() + " " + bestia.getNombre() + "!");
                }

                if (bestia.estaVivo()) {
                    int ataqueBestia = bestia.atacar(heroe);
                    if (ataqueBestia > 0) {
                        heroe.reducirVida(ataqueBestia);
                        System.out.println("    " + bestia.getNombre() + " saca " + ataqueBestia + " y le quita " + ataqueBestia + " de vida a " + heroe.getNombre());
                    } else {
                        System.out.println("    " + bestia.getNombre() + " saca " + ataqueBestia + " y no hace daño a " + heroe.getNombre());
                    }

                    if (!heroe.estaVivo()) {
                        System.out.println("  ¡Muere " + heroe.getClass().getSimpleName() + " " + heroe.getNombre() + "!");
                    }
                }
            }

            heroes.eliminarMuertos();
            bestias.eliminarMuertos();

            turno++;
            System.out.println();
        }

        if (heroes.estaVacio()) {
            System.out.println("¡¡VICTORIA DE LAS BESTIAS!!");
        } else {
            System.out.println("¡¡VICTORIA DE LOS HÉROES!!");
        }
    }
}
