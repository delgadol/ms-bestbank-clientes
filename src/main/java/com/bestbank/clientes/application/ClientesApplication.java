package com.bestbank.clientes.application;

import java.util.DuplicateFormatFlagsException;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.bestbank.clientes.application.utils.ApplicationConstants;
import com.bestbank.clientes.application.utils.BankFnUtils;
import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.service.ClienteService;
import com.bestbank.clientes.infrastructure.utils.ModelMapperUtils;
import com.bestbank.clientes.presentation.dto.ClienteModReq;
import com.bestbank.clientes.presentation.dto.ClienteReq;
import com.bestbank.clientes.presentation.dto.ClienteRes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClientesApplication {
  
  private final ClienteService servCliente;
  
  
  public ClientesApplication(ClienteService servCliente) {
    this.servCliente = servCliente;
  }
  
  public Flux<ClienteRes> getClients() {
    return ModelMapperUtils.mapToFlux(
      servCliente.findAllByIndEliminado(ApplicationConstants.REGISTRO_NO_ELIMINADO),
      ClienteRes.class);
  }

  public Mono<ClienteRes> getClientById(String idClient) {
    return esClienteValido(idClient)
      .flatMap(clienteEntidad -> {
        return Mono.just(ModelMapperUtils.map(clienteEntidad, ClienteRes.class));
      });       
  }

  public Mono<ClienteRes> putClient(String idClient, ClienteModReq cliente) {
    return esClienteValido(idClient)
      .flatMap(clienteEntidad -> {
        Cliente clienteModificado = new Cliente();
        clienteModificado = ModelMapperUtils.map(clienteEntidad, Cliente.class);
        clienteModificado.setNombres(cliente.getNombres());
        clienteModificado.setApellidos(cliente.getApellidos());
        return ModelMapperUtils.mapToMono(servCliente.save(clienteModificado), ClienteRes.class);
      });
  }

  public Mono<ClienteRes> postClient(ClienteReq cliente) {
    return servCliente.countByTipoDocumentoAndNumDocumento(cliente.getTipoDocumento(), 
      cliente.getNumDocumento())
      .filter(contReg -> contReg == 0)
      .flatMap(t -> {
        Cliente nuevoCliente = ModelMapperUtils.map(cliente, Cliente.class);
        nuevoCliente.setSecCtrl(BankFnUtils.uniqueProductCode());
        nuevoCliente.setEstado("0");
        nuevoCliente.setIndEliminado(0);
        return ModelMapperUtils.mapToMono(servCliente.save(nuevoCliente), ClienteRes.class);
      }).switchIfEmpty(Mono.error(
        new DuplicateFormatFlagsException(
          String.format("El Cliente %s %s ya esta registrado", cliente.getNombres(), 
              cliente.getApellidos())
          )
        )
      );
  }
  
  public Mono<ClienteRes> putClientState(String idClient, String stateClient) {
    return esClienteValido(idClient)
      .flatMap(clienteDB -> {
        Cliente modificadoCliente = ModelMapperUtils.map(clienteDB, Cliente.class);
        modificadoCliente.setEstado(stateClient);
        return ModelMapperUtils.mapToMono(servCliente.save(modificadoCliente), ClienteRes.class);
      });
    
  }
  
  public Mono<ClienteRes> delClient(String idClient) {
    return esClienteValido(idClient)
      .flatMap(clienteDB -> {
        Cliente modificadoCliente = ModelMapperUtils.map(clienteDB, Cliente.class);
        modificadoCliente.setIndEliminado(ApplicationConstants.REGISTRO_ELIMINADO);
        return ModelMapperUtils.mapToMono(servCliente.save(modificadoCliente), ClienteRes.class);
      });
    
  }
  
  
  /*
   * Metodos De Validación de Cliente Existente
   * 
   * 
   * */  
  private Mono<Cliente> esClienteValido(String idCliente) {
    return servCliente.findFirstByIdAndIndEliminado(idCliente, 
      ApplicationConstants.REGISTRO_NO_ELIMINADO)
      .filter(clienteDB -> !clienteDB.getId().isEmpty())
      .switchIfEmpty(Mono.error(
          new NoSuchElementException(
              String.format("%s no esta registrado", idCliente)
              )
            )
        );     
  }
  
  


}
