package com.bestbank.clientes.infrastructure.persistence;

import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.repository.ClientesRepository;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
