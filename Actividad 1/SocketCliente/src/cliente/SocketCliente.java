package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
				//salida.print(opcion);
				//System.out.println("Cliente: esperando respuesta...");
				
				switch (opcion) {
				case 1:
					System.out.println("Introduce el ID de la película:");
					String id = sc.nextLine();
					//Establecemos que la forma de pasar la información es opcion-información
					String info = String.valueOf(opcion) + "-" + id;
					System.out.println(info);
					//enviamos la opción elegida al servidor
					salida.println(info);
					System.out.println("Cliente: esperando respuesta...");
					String respuesta = entradaBuffer.readLine();
					System.out.println("Cliente: el servidor responde: " + respuesta);
					break;
				case 2:
					System.out.println("Introduce el título de la película: ");
					String titulo = sc.nextLine();
					String info1 = String.valueOf(opcion) + "-" + titulo;
					salida.println(info1);
					System.out.println("Cliente: esperando respuesta...");
					String respuesta1 = entradaBuffer.readLine();
					System.out.println("Cliente: el servidor responde: " + respuesta1);
					break;
					
				case 3:
					System.out.println("Introduce el director para consultar su filmografía disponible:");
					String director = sc.nextLine();
					String info2 = String.valueOf(opcion) + "-" + director;
					salida.println(info2);
					System.out.println("Cliente: esperando respuesta...");
					String respuesta2 = entradaBuffer.readLine();
					System.out.println("Cliente: el servidor responde: " + respuesta2);
				case 4:
//					System.out.println("Introduce el id de la película a añadir:");
//					String iDPeli = sc.nextLine();
					System.out.println("Introduce el título de la película a añadir:");
					String tituloPeli = sc.nextLine();
					System.out.println("Introduce el director a la película a añadir:");
					String directorPeli = sc.nextLine();
					System.out.println("Introduce el precio de la película a añadir:");
					String precioPeli = sc.nextLine();
					String info3 = String.valueOf(opcion) + "-" + tituloPeli + "-" + directorPeli + "-" + precioPeli;
					salida.println(info3);
					System.out.println("Cliente: esperando respuesta...");
					String respuesta3 = entradaBuffer.readLine();
					System.out.println("Cliente: el servidor responde: " + respuesta3);
					break;
					
					
				case 5:
					String info4 = String.valueOf(opcion);
					salida.println(info4);
					String respuesta4 = entradaBuffer.readLine();
					if("FIN".equalsIgnoreCase(respuesta4)) 
						continuar = false;
				default:
				}

				
			} while (continuar);
			socketAlServidor.close();
			
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		

		System.out.println("CLIENTE: Fin del programa");

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
