package br.com.alura.loja;

import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
  
  private static HttpServer server = null;
  
  public static HttpServer startaServidor() {
    ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
    URI uri = URI.create("http://localhost:8090/");
    server = GrizzlyHttpServerFactory.createHttpServer(uri,config);
    System.out.println("Servidor Rodando");
    return server;
  }
}