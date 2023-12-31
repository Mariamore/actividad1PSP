package dao;

import java.util.ArrayList;

import servidor.Pelicula;

public class Videoclub implements IntGestionPeliculas{
	private ArrayList<Pelicula> peliculas;
	
	public Videoclub() {
		peliculas = new ArrayList<>();
		cargarDatos();
	}

	@Override
	public Pelicula peliculaPorID(int idPelicula) {
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setID(idPelicula);
		int posicion = peliculas.indexOf(pelicula1);
		if (posicion == -1)
			return null;
		return peliculas.get(posicion);
		
	}

	@Override
	public ArrayList<Pelicula> peliculaPorTitulo(String tituloPelicula) {
		ArrayList<Pelicula> aux = new ArrayList<>();
		
		for(Pelicula pelicula: peliculas) {
			if(pelicula.getTitulo().equalsIgnoreCase(tituloPelicula)) {
				aux.add(pelicula);
			}
		}
		return aux;
	}

	@Override
	public ArrayList<Pelicula> peliculaPorDirector(String directorPelicula) {
		ArrayList<Pelicula> aux = new ArrayList<>();
		for ( Pelicula pelicula: peliculas) {
			if (pelicula.getDirector().equalsIgnoreCase(directorPelicula)) {
				aux.add(pelicula);
			}
		}
		return aux;
	}

	@Override
	public synchronized boolean alta(Pelicula pelicula) {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(peliculas.contains(pelicula)) {
			return false;
		} else {
			int generador = 1;
			
			for ( Pelicula pelicula1: peliculas) {
				while(pelicula1.getID() == generador) {
					generador++;
				}
				pelicula.setID(generador);
			}
			
			return peliculas.add(pelicula);
		}
		
	}
	
	private void cargarDatos() {
		peliculas.add(new Pelicula(1, "Titanic", "James Cameron" , 4.99));
		peliculas.add(new Pelicula(2, "Venom", "Ruben Fleischer" , 5.99));
		peliculas.add(new Pelicula(3, "La Ballena", "Daren Arnofsky" , 7.80));
		peliculas.add(new Pelicula(4, "As Bestas", "Rodrigo Sorogoyen" , 6.95));
		peliculas.add(new Pelicula(5, "El Reino", "Rodrigo Sorogoyen" , 11.50));
	}

	@Override
	public ArrayList<Pelicula> todasLasPeliculas() {
		return peliculas;
	}
	
	
}
