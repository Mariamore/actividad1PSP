package modelo.entidad;

import java.util.Objects;

public class Peliculas {
	
	private String id;
	private String titulo;
	private String director;
	private double precio;
	
	//Constructor
	public Peliculas(String id, String titulo, String director, double precio) {
	
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.precio = precio;
	}

	//getter and setters
	
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
		return "Peliculas [id=" + id + ", titulo=" + titulo + ", director=" + director + ", precio=" + precio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(director, id, precio, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peliculas other = (Peliculas) obj;
		return Objects.equals(director, other.director) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(titulo, other.titulo);
	}
	
	
	
	
	
	
	
	
	
	

}
