package br.com.alura.loja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {

  private HttpServer server;
  private Client client;
  private WebTarget target;
  private Carrinho carrinho2;
  
  @Before
  public void initTest(){
    server = Servidor.startaServidor();
    ClientConfig config = new ClientConfig();
    config.register(new LoggingFilter());
    client = ClientBuilder.newClient(config);
    target = client.target("http://localhost:8090");
  }

  @After
  public void endTest(){
    server.stop();
  }

  @Test
  public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
    assertTrue(carrinho.getRua().contains("Rua Vergueiro 3185, 8 andar"));
  }

  @Test
  public void testaQueSuportaNovosCarrinhos(){
    Carrinho carrinho = new Carrinho();
    carrinho.adiciona(new Produto(314, "Microfone", 37, 17));
    carrinho.setRua("Rua Vergueiro 3185, 8 andar");
    carrinho.setCidade("São Paulo");
    Entity<Carrinho> entity = Entity.entity(carrinho ,MediaType.APPLICATION_XML); 
    
    Response response = target.path("/carrinhos").request().post(entity);
    assertEquals(201,response.getStatus());

    String location = response.getHeaderString("Location");
    carrinho2 = client.target(location).request().get(Carrinho.class);
    assertEquals("Microfone",carrinho2.getProdutos().get(0).getNome());
  }

}
