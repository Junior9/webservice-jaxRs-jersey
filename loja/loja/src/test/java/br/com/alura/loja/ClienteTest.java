package br.com.alura.loja;

import static org.junit.Assert.assertTrue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.Test;

public class ClienteTest {

  @Test
  public void testaConexaoComServidor() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://www.mocky.io/");
    String conteudo =  target.path("v2/52aaf5bbee7ba8c60329fb7b").request().get(String.class);
    assertTrue(conteudo.contains("Rua Vergueiro 3185"));
  }
  
}
