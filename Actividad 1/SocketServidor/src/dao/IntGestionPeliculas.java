package dao;

import java.util.ArrayList;

import servidor.Pelicula;

public interface IntGestionPeliculas {

	Pelicula peliculaPorID(int idPelicula);
	ArrayList<Pelicula> peliculaPorTitulo(String tituloPelicula);
	ArrayList<Pelicula> peliculaPorDirector(String directorPelicula);
	boolean alta (Pelicula pelicula);
	ArrayList<Pelicula> todasLasPeliculas();
}
