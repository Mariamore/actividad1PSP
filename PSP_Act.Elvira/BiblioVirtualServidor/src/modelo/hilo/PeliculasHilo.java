package modelo.hilo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import modelo.dao.PeliculasDaoImpl;
import modelo.entidad.Peliculas;

public class PeliculasHilo implements Runnable {
    private PeliculasDaoImpl peliculasDao;
    private Socket socketCliente;

    public PeliculasHilo(PeliculasDaoImpl peliculasDao, Socket socketCliente) {
        this.peliculasDao = peliculasDao;
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            PrintStream salida = new PrintStream(socketCliente.getOutputStream());
            InputStreamReader entrada = new InputStreamReader(socketCliente.getInputStream());
            BufferedReader entradaBuffer = new BufferedReader(entrada);

            String texto = entradaBuffer.readLine();
            String[] datos = texto.split("-");

            String codigoPeticion = datos[0];
            String textoRespuesta = "";
            Peliculas peli = null;

            switch (codigoPeticion) {
                case "1":
                    String id = datos[1];
                    peli = peliculasDao.consultarPelicula(id);
                    textoRespuesta = serializarPelicula(peli);
                    break;
                case "2":
                    String titulo = datos[1];
                    peli = peliculasDao.consultarPeliculaTitulo(titulo);
                    textoRespuesta = serializarPelicula(peli);
                    break;
                case "3":
                    String director = datos[1];
                    List<Peliculas> peliculas = peliculasDao.consultarPeliculasPorDirector(director);
                    if (peliculas != null && !peliculas.isEmpty()) {
                        textoRespuesta = serializarPeliculas(peliculas);
                    } else {
                        textoRespuesta = "Error";
                    }
                    break;
                case "4":
                    peli = deserializarPelicula(datos);
                    boolean respuesta = peliculasDao.altaPelicula(peli);
                    textoRespuesta = String.valueOf(respuesta);
                    break;
                default:
                    textoRespuesta = "0";
                    break;
            }

            salida.println(textoRespuesta);
            socketCliente.close();
        } catch (Exception e) {
            System.err.println("Hilo error: Entrada/Salida");
            e.printStackTrace();
        }
    }

    private String serializarPeliculas(List<Peliculas> pelis) {
        StringBuilder peliculasSerializadas = new StringBuilder();
        for (Peliculas p : pelis) {
            peliculasSerializadas.append(serializarPelicula(p)).append("**");
        }
        return peliculasSerializadas.substring(0, peliculasSerializadas.length() - 2);
    }

    private String serializarPelicula(Peliculas peli) {
        if (peli != null) {
            return "*" + peli.getId() + "-" + peli.getTitulo() + "-" + peli.getDirector() + "-" + peli.getPrecio();
        } else {
            return "Error";
        }
    }

    private Peliculas deserializarPelicula(String[] datos) {
        Peliculas peli = new Peliculas(null, null, null, 0);
        peli.setId(datos[1]);
        peli.setTitulo(datos[2]);
        peli.setDirector(datos[3]);
        String sPrecio = datos[4];
        double precio = Double.parseDouble(sPrecio);
        peli.setPrecio(precio);
        return peli;
    }
}

