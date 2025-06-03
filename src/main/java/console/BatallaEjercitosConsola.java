package console;

import com.lotr.modelo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BatallaEjercitosConsola {

    public static void main(String[] args) {
    	// Este menú lo creo para poder correr la apps por consola
        List<Personaje> ejercitoHeroes = new ArrayList<>();
        List<Personaje> ejercitoBestias = new ArrayList<>();

        // Crear héroes
        ejercitoHeroes.add(new Humano("Aragorn", 150, 50));
        ejercitoHeroes.add(new Elfo("Legolas", 150, 30));
        ejercitoHeroes.add(new Humano("Gandalf", 300, 30));
        ejercitoHeroes.add(new Humano("Boromir", 100, 60));
        ejercitoHeroes.add(new Hobbit("Frodo", 20, 10));

        // Crear bestias
        ejercitoBestias.add(new Orco("Lurtz", 200, 60));
        ejercitoBestias.add(new Orco("Shagrat", 220, 50));
        ejercitoBestias.add(new Trasgo("Uglúk", 120, 30));
        ejercitoBestias.add(new Trasgo("Mauhúr", 100, 30));

        int turno = 1;

        System.out.println("⚔️ ¡Comienza la batalla entre HÉROES y BESTIAS!");

        while (!ejercitoHeroes.isEmpty() && !ejercitoBestias.isEmpty()) {
            System.out.println("\n🔁 Turno " + turno++ + ":");

            int enfrentamientos = Math.min(ejercitoHeroes.size(), ejercitoBestias.size());

            for (int i = 0; i < enfrentamientos; i++) {
                Personaje heroe = ejercitoHeroes.get(i);
                Personaje bestia = ejercitoBestias.get(i);

                int ataqueHeroe = heroe.atacar(bestia);
                int danioBestia = bestia.recibirDanio(ataqueHeroe);
                System.out.println(heroe.getNombre() + " ataca a " + bestia.getNombre() + " ➜ Daño: " + danioBestia);

                int ataqueBestia = bestia.atacar(heroe);
                int danioHeroe = heroe.recibirDanio(ataqueBestia);
                System.out.println(bestia.getNombre() + " contraataca a " + heroe.getNombre() + " ➜ Daño: " + danioHeroe);
            }

            // Eliminar muertos
            ejercitoHeroes.removeIf(p -> p.getVida() <= 0);
            ejercitoBestias.removeIf(p -> p.getVida() <= 0);
        }

        String ganador = ejercitoHeroes.isEmpty() ? "BESTIAS" : "HÉROES";
        System.out.println("\n🏆 ¡VICTORIA DE LAS " + ganador + "!");

        guardarResultado(ganador, ejercitoHeroes, ejercitoBestias);
    }

    private static void guardarResultado(String ganador, List<Personaje> heroes, List<Personaje> bestias) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("resultado_batalla.txt", true)))) {
            writer.println("🗓️ Batalla finalizada: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("🏆 Ganador: " + ganador);
            writer.println("💪 Sobrevivientes:");

            List<Personaje> sobrevivientes = ganador.equals("HÉROES") ? heroes : bestias;
            for (Personaje p : sobrevivientes) {
                writer.println("- " + p.getClass().getSimpleName() + " " + p.getNombre() + " | Vida: " + p.getVida());
            }

            writer.println("----------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Error al guardar resultado: " + e.getMessage());
        }
    }
}
