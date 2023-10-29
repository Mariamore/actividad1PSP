package actividad01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteActividad1 {
	
	public static final int PUERTO = 2581;
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
		
		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in)){
			System.out.println("******** CLIENTE ********");
			System.out.println("Esperando a que el servidor acepte la conexión...");
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("Conexion establecida a " + IP_SERVER + " por el puerto " + PUERTO);

			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			String texto = "";
			String textoEnviar = "";
			int opcion = 0;
			boolean continuar = true;
			do {
				System.out.println("-------- BIBLIOTECA DE PELÍCULAS --------");
				System.out.println("1. Consultar película por ID");
				System.out.println("2. Consultar película por título");
				System.out.println("3. Consultar películas por director");
				System.out.println("4. Añadir película");
				System.out.println("5. Salir del programa.");
				System.out.println("-----------------------------------------");
				System.out.println("Introduzca el número de la opción deseada...");
				opcion = sc.nextInt();
				
				switch(opcion) {
				case 1:
					System.out.println("Introduzca el ID de la película");
					Scanner sc1 = new Scanner(System.in);
					texto = sc1.nextLine();
					textoEnviar = "1-" + texto;
					break;
				case 2:
					System.out.println("Introduzca el título de la película");
					Scanner sc2 = new Scanner(System.in);
					texto = sc2.nextLine();
					textoEnviar = "2-" + texto;
					break;
				case 3:
					System.out.println("Introduzca el nombre del director");
					Scanner sc3 = new Scanner(System.in);
					texto = sc3.nextLine();
					textoEnviar = "3-" + texto;
					break;
				case 4:
					System.out.println("Introduzca el ID de la nueva película");
					Scanner sc4 = new Scanner(System.in);
					String textoId = sc4.nextLine();
					System.out.println("Introduzca el título de la nueva película");
					String textoTitulo = sc4.nextLine();
					System.out.println("Introduzca el director de la nueva película");
					String textoDirector = sc4.nextLine();
					System.out.println("Introduzca el precio de la nueva película");
					String textoPrecio = sc4.nextLine();
					textoEnviar = "4-" + textoId + "#" + textoTitulo + "#" + textoDirector + "#" + textoPrecio;
					break;
				case 5:
					textoEnviar = "END";
					break;
				default:
					textoEnviar = "OTHER";
				}
				
				salida.println(textoEnviar);
				System.out.println("Esperando respuesta del servidor...");				
				String respuesta = entradaBuffer.readLine();
					
				if("FIN".equalsIgnoreCase(respuesta)) {
					continuar = false;
				}else if("OTRO".equalsIgnoreCase(respuesta)) {
					System.out.println("Opción no válida...");
				}else if("ALTA".equalsIgnoreCase(respuesta)) {
					System.out.println("Película creada correctamente.");
				}else {
					System.out.println("********** RESULTADO BÚSQUEDA **********");
					System.out.println(respuesta);
					System.out.println("****************************************");
				}
			}while(continuar);
			socketAlServidor.close();
			System.out.println("Conexión finalizada. Gracias por utilizar nuestra filmoteca.");
		} catch (UnknownHostException e) {
			System.err.println("No se encuentra el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error -> " + e);
			e.printStackTrace();
		}


		
	}

}
