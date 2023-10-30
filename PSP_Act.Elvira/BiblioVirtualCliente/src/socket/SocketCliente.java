package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketCliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 2020;

    public static void main(String[] args) {
        Scanner entradaCliente = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            mostrarMenu();
            opcion = entradaCliente.nextInt();
            entradaCliente.nextLine();

            try (Socket socketCliente = new Socket(SERVIDOR, PUERTO);
                 Scanner entradaServidor = new Scanner(socketCliente.getInputStream())) {

                switch (opcion) {
                    case 1:
                        consultarPelicula(socketCliente, entradaCliente, entradaServidor);
                        break;
                    case 2:
                        consultarPeliculaTitulo(socketCliente, entradaCliente, entradaServidor);
                        break;
                    case 3:
                        consultarPeliculasPorDirector(socketCliente, entradaCliente, entradaServidor);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa.");
                        break;
                    case 5:
                        darAltaPelicula(socketCliente, entradaCliente, entradaServidor);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                System.err.println("Cliente: Error de E/S");
                e.printStackTrace();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Consultar película por ID");
        System.out.println("2. Consultar película por título");
        System.out.println("3. Consultar películas por director");
        System.out.println("4. Salir");
        System.out.println("5. Dar de alta una película");
        System.out.print("Elige una opción: ");
    }

    private static void consultarPelicula(Socket socketCliente, Scanner entradaCliente, Scanner entradaServidor) {
        try {
            
            System.out.print("Dame el ID de la película: ");
            String id = entradaCliente.nextLine();
            PrintStream salida = new PrintStream(socketCliente.getOutputStream());
            salida.println("1-" + id);

            String respuesta = entradaServidor.nextLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            System.err.println("Cliente: Error de E/S");
            e.printStackTrace();
        }
    }

    private static void consultarPeliculaTitulo(Socket socketCliente, Scanner entradaCliente, Scanner entradaServidor) {
        try {
            
            System.out.print("Dame el título de la película: ");
            String titulo = entradaCliente.nextLine();//eliminamos espacios en blanco
            PrintStream salida = new PrintStream(socketCliente.getOutputStream());
            salida.println("2-" + titulo);

            String respuesta = entradaServidor.nextLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            System.err.println("Cliente: Error de E/S");
            e.printStackTrace();
        }
    }

    private static void consultarPeliculasPorDirector(Socket socketCliente, Scanner entradaCliente, Scanner entradaServidor) {
        try {
            
            System.out.print("Dame el nombre del director: ");
            String director = entradaCliente.nextLine();
            PrintStream salida = new PrintStream(socketCliente.getOutputStream());
            salida.println("3-" + director);

            String respuesta = entradaServidor.nextLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            System.err.println("Cliente: Error de E/S");
            e.printStackTrace();
        }
    }

    private static void darAltaPelicula(Socket socketCliente, Scanner entradaCliente, Scanner entradaServidor) {
        try {
            // Agrega el código para dar de alta una película
            PrintStream salida = new PrintStream(socketCliente.getOutputStream());
            entradaCliente.nextLine();
            System.out.print("Dame el ID de la película: ");
            String id = entradaCliente.next();
            System.out.print("Dame el título de la película: ");
            String titulo = entradaCliente.nextLine();
            System.out.print("Dame el director de la película: ");
            String director = entradaCliente.nextLine();
            System.out.print("Dame el precio de la película: ");
            double precio = entradaCliente.nextDouble();
            salida.println("4-" + id + "-" + titulo + "-" + director + "-" + precio);

            String respuesta = entradaServidor.nextLine();
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (IOException e) {
            System.err.println("Cliente: Error de E/S");
            e.printStackTrace();
        }
    }
}
