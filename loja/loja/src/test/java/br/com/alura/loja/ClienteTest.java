package br.com.alura.loja;

import static org.junit.Assert.assertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.xstream.XStream;
import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {

  private HttpServer server;
  
  @Before
  public void initTest(){
    server = Servidor.startaServidor();
  }
  
  @After
  public void endTest(){
    server.stop();
  }
  
  @Test
  public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8090");
    String conteudo =  target.path("/carrinhos").request().get(String.class);
    Carrinho carrinho = (Carrinho) new  XStream().fromXML(conteudo);
    assertTrue(carrinho.getRua().contains("Rua Vergueiro 3185, 8 andar"));
  }
}
