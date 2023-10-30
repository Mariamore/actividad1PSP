package actividad01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorActividad1 {
	
	public static final int PUERTO = 2581;

	public static void main(String[] args) {
		
		try (ServerSocket server = new ServerSocket();){
			System.out.println("******** SERVIDOR ********");
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			server.bind(direccion);
			System.out.println("Esperando petición por el puerto " + PUERTO);
			while (true) {
				Socket socketAlCliente = server.accept();
				System.out.println("Petición recibida");
				new HiloPeliculas(socketAlCliente);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
