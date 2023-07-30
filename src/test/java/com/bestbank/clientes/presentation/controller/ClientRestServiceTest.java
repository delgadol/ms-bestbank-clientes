package com.bestbank.clientes.presentation.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.bestbank.clientes.application.ClientesApplication;
import com.bestbank.clientes.application.utils.TestUtils;
import com.bestbank.clientes.presentation.dto.ClienteModReq;
import com.bestbank.clientes.presentation.dto.ClienteReq;
import com.bestbank.clientes.presentation.dto.ClienteRes;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@WebFluxTest(ClienteRestService.class)
public class ClientRestServiceTest {
  
  @MockBean
  ClientesApplication clientesApp;
  
  @Autowired
  WebTestClient webTestClient;
  
  @Test
  void whenGetAllClientHaveItems() throws IOException {
    
    List<ClienteRes> clientes = 
        (List<ClienteRes>) TestUtils.readJsonFromPath("mocks/getAllClientsFromRest.json", 
            new TypeReference<List<ClienteRes>>() {});
    
   Mockito.when(clientesApp.getClients())
     .thenReturn(Flux.fromIterable(clientes));
    
    
    webTestClient
      .get()
      .uri("/v1/clientes")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBodyList(ClienteRes.class)
      .consumeWith(respose -> { 
        List<ClienteRes> items = respose.getResponseBody();
        Assertions.assertTrue(items.size()>0);
      });
      
  }
  
  @Test
  void whenGetClientByIdHaveItem() throws IOException {
    
   ClienteRes cliente = 
        (ClienteRes) TestUtils.readJsonFromPath("mocks/getClientByIdFromRest.json", 
            new TypeReference<ClienteRes>() {});
    
   Mockito.when(clientesApp.getClientById("1L"))
     .thenReturn(Mono.just(cliente));
    
    
    webTestClient
      .get()
      .uri("/v1/clientes/1L")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody(ClienteRes.class)
      .consumeWith(respose -> { 
        ClienteRes item = respose.getResponseBody();
        Assertions.assertEquals(item .getId(), cliente.getId());
      });
  }
  
  
  @Test
  void whenPutClientIsOK() throws IOException {
    
    ClienteModReq clientModReq = 
        (ClienteModReq) TestUtils.readJsonFromPath("mocks/putClientReq.json", 
            new TypeReference<ClienteModReq>() {});
    
    ClienteRes cliente = 
        (ClienteRes) TestUtils.readJsonFromPath("mocks/getClientByIdFromRest.json", 
            new TypeReference<ClienteRes>() {});
    
    Mockito.when(clientesApp.putClient(anyString(), any(ClienteModReq.class)))
    .thenReturn(Mono.just(cliente));
    
    webTestClient
    .put()
    .uri(String.format("/v1/clientes/%s",cliente.getId()))
    .body(Mono.just(clientModReq),ClienteModReq.class)
    .exchange()
    .expectStatus()
    .isOk()
    .expectBody(ClienteRes.class)
    .consumeWith(respose -> { 
      ClienteRes item = respose.getResponseBody();
      Assertions.assertEquals(item .getId(), cliente.getId());
    });
    
  }
  
  @Test
  void whenPostClientIsOK() throws IOException {
    
    ClienteReq clientReq = 
        (ClienteReq) TestUtils.readJsonFromPath("mocks/postClientReq.json", 
            new TypeReference<ClienteReq>() {});
    
    ClienteRes cliente = 
        (ClienteRes) TestUtils.readJsonFromPath("mocks/getClientByIdFromRest.json", 
            new TypeReference<ClienteRes>() {});
    
    Mockito.when(clientesApp.postClient(any(ClienteReq.class)))
    .thenReturn(Mono.just(cliente));
    
    webTestClient
    .post()
    .uri("/v1/clientes")
    .body(Mono.just(clientReq),ClienteModReq.class)
    .exchange()
    .expectStatus()
    .isOk()
    .expectBody(ClienteRes.class)
    .consumeWith(respose -> { 
      ClienteRes item = respose.getResponseBody();
      Assertions.assertEquals(item .getId(), cliente.getId());
    });
    
  }
  
  @Test
  void whenDeleteClientIsOk() throws IOException {
    ClienteRes cliente = 
        (ClienteRes) TestUtils.readJsonFromPath("mocks/getClientByIdFromRest.json", 
            new TypeReference<ClienteRes>() {});
    
    Mockito.when(clientesApp.delClient(anyString()))
    .thenReturn(Mono.just(cliente));
    
    webTestClient
    .delete()
    .uri(String.format("/v1/clientes/%s",cliente.getId()))
    .exchange()
    .expectStatus()
    .isOk()
    .expectBody(ClienteRes.class)
    .consumeWith(respose -> { 
      ClienteRes item = respose.getResponseBody();
      Assertions.assertEquals(item.getId(), cliente.getId());
    });
  }

  @Test
  void whenPutEstadoClientIsOk() throws IOException {
    ClienteRes cliente = 
        (ClienteRes) TestUtils.readJsonFromPath("mocks/getClientByIdFromRest.json", 
            new TypeReference<ClienteRes>() {});
    
    Mockito.when(clientesApp.putClientState(anyString(), anyString()))
    .thenReturn(Mono.just(cliente));
    
    webTestClient
    .put()
    .uri(String.format("/v1/clientes/%s/estado/%s",cliente.getId(),"0"))
    .exchange()
    .expectStatus()
    .isOk()
    .expectBody(ClienteRes.class)
    .consumeWith(respose -> { 
      ClienteRes item = respose.getResponseBody();
      Assertions.assertEquals(item.getId(), cliente.getId());
    });
  }
}
