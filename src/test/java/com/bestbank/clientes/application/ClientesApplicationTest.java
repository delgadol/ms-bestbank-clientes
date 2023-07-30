package com.bestbank.clientes.application;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import com.bestbank.clientes.application.services.ClienteService;
import com.bestbank.clientes.application.utils.TestUtils;
import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;
import com.bestbank.clientes.presentation.dto.ClienteModReq;
import com.bestbank.clientes.presentation.dto.ClienteReq;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ClientesApplicationTest {

  @Test
  @Disabled
  void test() {
    fail("Not yet implemented");
  }
  
  @Mock
  ClienteService servCliente;
  
  @InjectMocks
  ClientesApplication clientesApp;
  
  @Test
  void whenGetClientsIsOk() throws IOException {
    
    List<Cliente> clientes = 
        (List<Cliente>) TestUtils.readJsonFromPath("mocks/findClientsList.json", 
            new TypeReference<List<Cliente>>() {});
    
    Mockito.when(servCliente.findAllByIndEliminado(0))
      .thenReturn(Flux.fromIterable(clientes));
    
    StepVerifier.create(clientesApp.getClients())
      .assertNext( clienteRes -> { Assertions.assertNotNull(clienteRes.getId()); })
      .verifyComplete();
    
  }
 
  @Test
  void whenGetClientByIdIsOk() throws IOException {
    
    Cliente cliente = TestUtils.readJsonFromPath("mocks/findClientById.json", 
        new TypeReference<Cliente>() {});
    
    Mockito.when(servCliente.findFirstByIdAndIndEliminado("1L", 0))
    .thenReturn(Mono.just(cliente));
    
    StepVerifier.create(clientesApp.getClientById("1L"))
    .assertNext(t -> { Assertions.assertEquals("64c46a358724896e2b8b5aa4", t.getId()); })
    .verifyComplete();    
    
  }
  
  @Test
  void whenGetClientByIdIsError() throws IOException {
    
    Mockito.when(servCliente.findFirstByIdAndIndEliminado("1L", 0))
    .thenReturn(Mono.error(new Throwable("1L no esta registrado")));
    
    StepVerifier.create(clientesApp.getClientById("1L"))
    .consumeErrorWith(t -> { Assertions.assertEquals("1L no esta registrado", t.getMessage()); }); 
    
  }
  
  @Test
  void whenPostClientIsOK() throws IOException {
    
    ClienteReq clienteReq = TestUtils.readJsonFromPath("mocks/postClientReq.json", 
        new TypeReference<ClienteReq>() {});
    
    Cliente cliente = TestUtils.readJsonFromPath("mocks/findClientById.json", 
        new TypeReference<Cliente>() {});
    
    Mockito.when(servCliente.countByTipoDocumentoAndNumDocumento(TipoDocumento.DNI, "123456789124"))
      .thenReturn(Mono.just(0L));
    
    Mockito.when(servCliente.save(any(Cliente.class)))
      .thenReturn(Mono.just(cliente));
    
    StepVerifier.create(clientesApp.postClient(clienteReq))
      .assertNext(t -> { Assertions.assertEquals(clienteReq.getTipoCliente(), t.getTipoCliente()); } )
      .verifyComplete();    
    
  }
  
  @Test
  void whenClientModIsOk() throws IOException {
    
    ClienteModReq clienteMod = TestUtils.readJsonFromPath("mocks/putClientReq.json", 
        new TypeReference<ClienteModReq>() {}); 
    
    Cliente cliente = TestUtils.readJsonFromPath("mocks/findClientById.json", 
        new TypeReference<Cliente>() {});
    
    Mockito.when(servCliente.findFirstByIdAndIndEliminado(cliente.getId(), 0))
    .thenReturn(Mono.just(cliente));
    
    Mockito.when(servCliente.save(any(Cliente.class)))
    .thenReturn(Mono.just(cliente));
    
    StepVerifier.create(clientesApp.putClient(cliente.getId(), clienteMod))
      .assertNext(t -> { Assertions.assertEquals(cliente.getNombres(), t.getNombres());})
      .verifyComplete();    
    
  }
  
  @Test
  void whenPutStateClientIsOk() throws IOException {
    
    Cliente cliente = TestUtils.readJsonFromPath("mocks/findClientById.json", 
        new TypeReference<Cliente>() {});
    
    Mockito.when(servCliente.findFirstByIdAndIndEliminado(cliente.getId(), 0))
    .thenReturn(Mono.just(cliente));
    
    Mockito.when(servCliente.save(any(Cliente.class)))
    .thenReturn(Mono.just(cliente));
    
    StepVerifier.create(clientesApp.putClientState(cliente.getId(), "0"))
      .assertNext(t -> { Assertions.assertEquals(cliente.getNombres(), t.getNombres());})
      .verifyComplete();    
    
  }
  
  @Test
  void whenDeleteClientIsOk() throws IOException {
    
    Cliente cliente = TestUtils.readJsonFromPath("mocks/findClientById.json", 
        new TypeReference<Cliente>() {});
    
    Cliente clienteEliminado = TestUtils.readJsonFromPath("mocks/delClientById.json", 
        new TypeReference<Cliente>() {});
    
    Mockito.when(servCliente.findFirstByIdAndIndEliminado(cliente.getId(), 0))
    .thenReturn(Mono.just(cliente));
    
    Mockito.when(servCliente.save(any(Cliente.class)))
    .thenReturn(Mono.just(clienteEliminado));
    
    StepVerifier.create(clientesApp.delClient(cliente.getId()))
      .assertNext(t -> { Assertions.assertEquals(cliente.getIndEliminado(), 1);})
      .verifyComplete();    
    
  }


}
