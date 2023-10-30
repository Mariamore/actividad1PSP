package modelo.dao;

import java.util.ArrayList;
import java.util.List;
import modelo.entidad.Peliculas;

public class PeliculasDaoImpl implements PeliculasDao {
    private ArrayList<Peliculas> peliculas;

    public PeliculasDaoImpl() {
        peliculas = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        peliculas.add(new Peliculas("A001", "Lo que el viento se llevó", "VictorFleming", 12.99));
        peliculas.add(new Peliculas("A002", "Los vengadores, la era de Ultron", "Joss Whedon", 15.99));
        peliculas.add(new Peliculas("A003", "Harry Potter y el prisionero de Azkaban", "AlfonsoCuaron", 17.99));
        peliculas.add(new Peliculas("A004", "El señor de los anillos, las dos torres", "PeterJackson", 19.99));
        peliculas.add(new Peliculas("A005", "Iron Man 3", "Shane Black", 12.99));
        peliculas.add(new Peliculas("A006", "Roma", "AlfonsoCuaron", 15.99));
    }

    public Peliculas consultarPelicula(String id) {
        for (Peliculas p : peliculas) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public Peliculas consultarPeliculaTitulo(String titulo) {
        for (Peliculas p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

    public List<Peliculas> consultarPeliculasPorDirector(String director) {
        List<Peliculas> aux = new ArrayList<>();
        for (Peliculas p : peliculas) {
            if (p.getDirector().equalsIgnoreCase(director)) {
                aux.add(p);
            }
        }
        return aux;
    }

    public boolean altaPelicula(Peliculas pelicula) {
        if (!peliculas.contains(pelicula)) {
            return peliculas.add(pelicula);
        }
        return false;
    }
}
