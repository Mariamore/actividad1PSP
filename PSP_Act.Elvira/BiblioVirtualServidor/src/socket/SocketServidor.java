package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import modelo.dao.PeliculasDaoImpl;
import modelo.hilo.PeliculasHilo;

public class SocketServidor {
    public static final int PUERTO = 2020;

    public static void main(String[] args) {
        System.out.println("----------- APLICACIÓN SERVIDOR CON HILOS ------------");
        System.out.println("______________________________________________________");

        PeliculasDaoImpl pelisDao = instanciarDao();

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("SERVIDOR: Esperando peticiones en el puerto " + PUERTO);

            int peticion = 0;

            while (true) {
                System.out.println("SERVIDOR: Esperando petición número: " + ++peticion);

                Socket socketCliente = serverSocket.accept();

                System.out.println("SERVIDOR: Petición número: " + peticion + " recibida");

                PeliculasHilo hl = new PeliculasHilo(pelisDao, socketCliente);
                Thread hilo = new Thread(hl);
                hilo.start();
            }
        } catch (IOException e) {
            System.err.println("SERVIDOR: ERROR ENTRADA/SALIDA");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("SERVIDOR: ERROR -> " + e);
            e.printStackTrace();
        }
    }

    private static PeliculasDaoImpl instanciarDao() {
        return new PeliculasDaoImpl();
    }
}




