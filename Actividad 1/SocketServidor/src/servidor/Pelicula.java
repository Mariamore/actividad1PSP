package servidor;

import java.util.Objects;

public class Pelicula {

	private int iD;
	
	private String titulo, director;
	private double precio;
	
	public Pelicula(int iD, String titulo, String director, double precio) {
		super();
		this.iD = iD;
		this.titulo = titulo;
		this.director = director;
		this.precio = precio;
	}

	
	public Pelicula() {
		super();
	}


	//Creamos los getters y setters
	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
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

	//Generamos el equals y hashCode para que dos objetos sean iguales por su identificador unívoco 
	@Override
	public int hashCode() {
		return Objects.hash(iD);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return iD == other.iD;
	}

	//Generamos el toString 
	@Override
	public String toString() {
		return "Pelicula [iD=" + iD + ", titulo=" + titulo + ", director=" + director + ", precio=" + precio + "]";
	}

	
	
}
