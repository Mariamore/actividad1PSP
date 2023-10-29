package actividad01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HiloPeliculas implements Runnable{
	
	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;
	
	public HiloPeliculas (Socket socketAlCliente) {
		numCliente++;
		hilo = new Thread(this, "Cliente_"+numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		List<Pelicula> listaPeliculas = Pelicula.cargarPeliculas();
		
		try {
			salida = new PrintStream(socketAlCliente.getOutputStream());
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			
			String textoRecibido = "";
			boolean continuar = true;
			
			while(continuar) {
				textoRecibido = entradaBuffer.readLine();
				if (textoRecibido.trim().equalsIgnoreCase("END")) {
					salida.println("FIN");
					System.out.println(hilo.getName() + " ha cerrado la comunicacion");
					continuar = false;
				} else if (textoRecibido.trim().equalsIgnoreCase("OTHER")) {
					salida.println("OTRO");
				} else {
					String textoTotal[] = textoRecibido.split("-");
					int opcion = Integer.parseInt(textoTotal[0]);
					String textoBuscar = textoTotal[1];
					
					switch(opcion) {
					case 1:
						List<Pelicula> peliculasId = new ArrayList<Pelicula>();
						for(int i=0; i<listaPeliculas.size(); i++) {
							if (textoBuscar.trim().equalsIgnoreCase(listaPeliculas.get(i).getId())) {
								peliculasId.add(listaPeliculas.get(i));
							}
						}
						salida.println(peliculasId);
						break;
					case 2:
						List<Pelicula> peliculasTitulo = new ArrayList<Pelicula>();
						for(int i=0; i<listaPeliculas.size(); i++) {
							if (textoBuscar.trim().equalsIgnoreCase(listaPeliculas.get(i).getTitulo())) {
								peliculasTitulo.add(listaPeliculas.get(i));
							}
						}
						salida.println(peliculasTitulo);
						break;
					case 3:
						List<Pelicula> peliculasDirector = new ArrayList<Pelicula>();
						for(int i=0; i<listaPeliculas.size(); i++) {
							if (textoBuscar.trim().equalsIgnoreCase(listaPeliculas.get(i).getDirector())) {
								peliculasDirector.add(listaPeliculas.get(i));
							}
						}
						salida.println(peliculasDirector);
						break;
					case 4:
						String nuevaPelicula[] = textoBuscar.split("#");
						Pelicula pelicula = new Pelicula();
						pelicula.setId(nuevaPelicula[0]);
						pelicula.setTitulo(nuevaPelicula[1]);
						pelicula.setDirector(nuevaPelicula[2]);
						pelicula.setPrecio(Double.parseDouble(nuevaPelicula[3]));
						Pelicula.altaPeliculas(pelicula,listaPeliculas);
						salida.println("ALTA");
						break;
					default:
						salida.println("OTRO");
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
