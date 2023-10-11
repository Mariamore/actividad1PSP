package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidor {
	
	//Definimos el puerto, que será el mismo que el del cliente
	public static final int PUERTO = 2017;
	
	public static void main (String[] args) {
		
		System.out.println(" ------ APLICACIÓN DEL SERVIDOR ------ ");
		System.out.println(" ------------------------------------- ");
		
		//establecemos una variable de tipo entero llamada petición para poder contar el número de peticiones recibidas
		int peticion = 0;
		
		//abrimos un try-with-resources
		
		try(ServerSocket servidor = new ServerSocket();){
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			
			//decimos al socket que escuche peticiones desde el puerto que hemos establecido
			servidor.bind(direccion);
			
			System.out.println("Servidor: Esperando petición por el puerto " + PUERTO);
			
			while(true) {
				//Por cada petición crearemos un objeto socket distinto
				Socket socketAlCliente = servidor.accept();
				System.out.println("Servidor: peticion número " + ++peticion + " recibida.");
				
				//Abrimos un hilo nuevo para liberar el servidor y que pueda aceptar otras peticiones
				new HiloPelicula(socketAlCliente);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
