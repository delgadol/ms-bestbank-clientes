package com.bestbank.clientes.domain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientesRepository extends ReactiveMongoRepository<Cliente, String> {

  Flux<Cliente> findAllByIndEliminado(int indEliminado); 
  
  Mono<Long> countByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento, String numDocumento);
  
  Mono<Cliente> findFirstByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento,
      String numDocumento);
  
  Mono<Cliente> findFirstByIdAndIndEliminado(String id, Integer indEliminado);
  
  Mono<Long> countByIdAndIndEliminado(String id, Integer indEliminado);
  
}
