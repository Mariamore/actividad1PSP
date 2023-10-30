package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Peliculas;



public interface PeliculasDao {

	Peliculas consultarPelicula(String id);
	Peliculas consultarPeliculaTitulo (String titulo);
	List<Peliculas> consultarPeliculasPorDirector(String director);
	boolean altaPelicula(Peliculas pelicula);
}
	
    //Consultar película por ID
    //Consultar película por título
    //Salir de la aplicación


