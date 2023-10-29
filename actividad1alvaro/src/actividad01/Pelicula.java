package actividad01;

import java.util.ArrayList;
import java.util.List;

public class Pelicula{
	
	private String id;
	private String titulo;
	private String director;
	private double precio;
	
	public Pelicula(String id, String titulo, String director, double precio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.precio = precio;
	}

	public Pelicula() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "ID: " + id + ". Título: " + titulo + ". Director: " + director + ". Precio: " + precio + "€";
	}
	
	public static List<Pelicula> cargarPeliculas() {
		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
		listaPeliculas.add(new Pelicula ("1", "El resplandor", "Stanley Kubrick", 19.95));
		listaPeliculas.add(new Pelicula("2", "La naranja mecánica", "Stanley Kubrick", 15.99));
		listaPeliculas.add(new Pelicula("3", "Blade Runner", "Ridley Scott", 22.50));
		listaPeliculas.add(new Pelicula("4", "American Beauty", "Sam Mendes", 9.95));
		listaPeliculas.add(new Pelicula("5", "Carretera perdida", "David Lynch", 12.25));
		return listaPeliculas;
	}
	
	public static synchronized void altaPeliculas(Pelicula pelicula, List<Pelicula> listaPeliculas) {
		listaPeliculas.add(pelicula);
	}

}
