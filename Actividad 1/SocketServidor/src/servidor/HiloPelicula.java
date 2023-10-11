package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


import dao.Videoclub;

public class HiloPelicula implements Runnable{
	
	private Thread hilo;
	private Socket socketAlCliente;
	private int numCliente = 0;
	

	public HiloPelicula (Socket socketAlCliente) {
		numCliente++;
		
		hilo =  new Thread (this, "Cliente-" + numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}
	
	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
			
			//Establecemos la salida del servidor al cliente
			salida = new PrintStream(socketAlCliente.getOutputStream());
			//Establecemos al entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			Videoclub peliculas = new Videoclub();
			
			String info = "";
			
			boolean continuar = true;
			
			while (continuar) {
				info = entradaBuffer.readLine();
				String[] partes = info.split("-");
				String opcion = partes[0];
				
				switch (opcion) {
				case "1":
					String iDString = partes[1];
					int iD = Integer.parseInt(iDString);
					System.out.println(hilo.getName() + " ha mandado la id " + iD + " y corresponde a la película " + peliculas.peliculaPorID(iD));
					salida.println(peliculas.peliculaPorID(iD));
					break;
					
				case "2":
					String titulo = partes[1];
					System.out.println(hilo.getName() + " ha mandado el título " + titulo + " y corresponde a la película " + peliculas.peliculaPorTitulo(titulo));
					salida.println(peliculas.peliculaPorTitulo(titulo));
					break;
					
				case "3":
					String director = partes[1];
					System.out.println(hilo.getName() + " ha mandado el director " + director + " y corresponde a la película " + peliculas.peliculaPorDirector(director));
					salida.println(peliculas.peliculaPorDirector(director));
					break;
					
				case "4":
					Pelicula peli = new Pelicula();
					
					peli.setTitulo(partes[1]);
					peli.setDirector(partes[2]);
					peli.setPrecio(Double.parseDouble(partes[3]));
					System.out.println(hilo.getName() + " ha mandado la película" + peli);
					Thread.sleep(10000);
					peliculas.alta(peli);
					salida.println(peliculas.todasLasPeliculas());
					break;
					
				case "5":
					
				default:
				}
//				if (info.charAt(0) == '1') {
//					System.out.println("Ha llegado algo");
//					String iDString = info.substring(2);
//					int iD = Integer.parseInt(iDString);
//					System.out.println(hilo.getName() + " ha mandado la id " + iD + "y corresponde a la película " + peliculas.peliculaPorID(iD));
//					
//					salida.println(peliculas.peliculaPorID(iD));
//					break;
//				}
				}
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
