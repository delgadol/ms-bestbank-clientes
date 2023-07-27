package com.bestbank.clientes.presentation.controller;

import com.bestbank.clientes.application.ClientesApplication;
import com.bestbank.clientes.presentation.dto.ClienteModReq;
import com.bestbank.clientes.presentation.dto.ClienteReq;
import com.bestbank.clientes.presentation.dto.ClienteRes;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que proporciona servicios REST para la gestión de clientes.
 */
@RestController
@Validated
@RequestMapping("/v1/clientes")
public class ClienteRestService {
  
  private final ClientesApplication clientesApp;
  
  
  public ClienteRestService(ClientesApplication clientesApp) {
    this.clientesApp = clientesApp;
  }

  /**
   * Obtiene todos los clientes.
   *
   * @return un Flux que emite objetos ClienteRes correspondientes a todos los clientes
   */

  @GetMapping(path = {"","/"})
  public Flux<ClienteRes> getClients() {
    return clientesApp.getClients();
  }
  
  /**
   * Obtiene un cliente por su identificador.
   *
   * @param idClient el identificador del cliente
   * @return un Mono que emite el objeto ClienteRes correspondiente al ID proporcionado
   */

  @GetMapping("/{id}")
  public Mono<ClienteRes> getClientById(@PathVariable(name = "id") String idClient) {
    return clientesApp.getClientById(idClient);
  }
  
  /**
   * Actualiza un cliente existente con la información proporcionada.
   *
   * @param idClient el identificador del cliente a actualizar
   * @param cliente la información actualizada del cliente
   * @return un Mono que emite el objeto ClienteRes resultante
   */
  @PutMapping("/{id}")
  public Mono<ClienteRes> putClient(@PathVariable(name = "id") String idClient, 
      @Valid @RequestBody ClienteModReq cliente) {
    return clientesApp.putClient(idClient, cliente);
  }
  
  /**
   * Crea un nuevo cliente con la información proporcionada.
   *
   * @param cliente la información del cliente a crear
   * @return un Mono que emite el objeto ClienteRes resultante
   */
  @PostMapping("")
  public Mono<ClienteRes> postClient(@Valid @RequestBody ClienteReq cliente) {
    return clientesApp.postClient(cliente);
  }
  

  /**
   * Elimina un cliente por su identificador.
   *
   * @param idClient el identificador del cliente a eliminar
   * @return un Mono que emite el objeto ClienteRes correspondiente al cliente eliminado
   */
  @DeleteMapping("/{id}")
  public Mono<ClienteRes> delClient(@PathVariable(name = "id") String idClient) {
    return clientesApp.delClient(idClient);
  }
  
  /**
   * Actualiza el estado de un cliente existente.
   *
   * @param idClient el identificador del cliente a actualizar
   * @param stateClient el estado actualizado del cliente
   * @return un Mono que emite el objeto ClienteRes resultante
   */
  @PutMapping("/{id}/estado/{idEstado}")
  public Mono<ClienteRes> putClientState(@PathVariable(name = "id") String idClient, 
      @PathVariable(name = "idEstado") String stateClient) {
    return clientesApp.putClientState(idClient, stateClient);
  }

}
