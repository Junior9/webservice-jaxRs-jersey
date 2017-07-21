package br.com.alura.loja;

import java.io.IOException;
import org.glassfish.grizzly.http.server.HttpServer;

public class Main {
    public static void main(String[] args) throws IOException {
      HttpServer servidor = Servidor.startaServidor();
      System.in.read();
      servidor.stop();
    }
}
