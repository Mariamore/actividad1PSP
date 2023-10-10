package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente {

	//Fijamos la IP y puerto al que nos vamos a conectar
	
	public static final int PUERTO = 2017;
	public static final String IP_SERVER = "localhost";
	
	public static void main (String []args) {
		System.out.println(" ------ APLICACIÓN CLIENTE ------ ");
		System.out.println(" -------------------------------- ");
		
		//Procedemos a encapsular la IP y el puerto al que nos conectaremos
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		//Usamos un try-with-resources
		
		try(Scanner sc = new Scanner(System.in);){
			
			System.out.println("Cliente: esperando que el servidor acepte la conexión...");
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("Cliente: conexión establecida a " + IP_SERVER + " por el puerto " + PUERTO);
			
			//Ahora creamos el objeto para poder leer la salida del servidor
			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			//Ahora utilizamos la clase BufferedReader para poder leer la salida del servidor línea a línea
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			//Ahora creamos el objeto que nos permite mandar información al servidor
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			//Creamos la variable continuar, que nos va a permitir mantener la conexión con el servidor mientras su valor sea true
			boolean continuar = true;
			//Abrimos el bucle do while
			
			do {
				int opcion = menu();
				
				//enviamos la opción elegida al servidor
				salida.println(opcion);
				System.out.println("Cliente: esperando respuesta...");
				
			} while (continuar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static int menu() {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		 System.out.println(" ------ MENÚ ------- ");
		 System.out.println("1. Consultar película por ID");
		 System.out.println("2. Consultar película por título");
		 System.out.println("3. Consultar películas por director");
		 System.out.println("4. Añadir película");
		 System.out.println("5. Salir");
		 
		 while(opcion > 5 || opcion < 1) {
			 System.out.println("Introduce el número de la opción elegida:");
			 opcion = sc.nextInt();
		 }
		 return opcion;
		
	}
	
	
}
