package com.bestbank.clientes.infrastructure.persistence;

import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.repository.ClientesRepository;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase abstracta que proporciona una implementación base para el repositorio de clientes.
 * Las implementaciones concretas deben extender esta clase y proporcionar la lógica específica 
 * del repositorio.
 */
public  abstract class ClientesRepositoryImpl {
  
  protected final ClientesRepository clientesRepo;

    
  protected ClientesRepositoryImpl(ClientesRepository clientesRepo) {
    super();
    this.clientesRepo = clientesRepo;
  }

  public abstract Flux<Cliente> findAllByIndEliminado(int indEliminado);
  
  public abstract Mono<Long> countByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento, 
      String numDocumento);
  
  public abstract Mono<Cliente> findFirstByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento,
      String numDocumento); 
  
  public abstract Mono<Cliente> findFirstByIdAndIndEliminado(String id, Integer indEliminado);
  
  public abstract Mono<Long> countByIdAndIndEliminado(String id, Integer indEliminado);
  
  public abstract Mono<Cliente> save(Cliente cliente);
  
  

}
