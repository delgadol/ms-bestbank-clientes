package com.bestbank.clientes.infrastructure.persistence;

import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.repository.ClientesRepository;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ClientesRepositoryImpl {
  
  private final ClientesRepository clientesRepo;  
  
  public ClientesRepositoryImpl(ClientesRepository clientesRepo) {
    this.clientesRepo = clientesRepo;
  }

  Flux<Cliente> findAllByIndEliminado(int indEliminado) {
    return clientesRepo.findAllByIndEliminado(indEliminado);
  } 
  
  Mono<Long> countByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento, String numDocumento) {
    return clientesRepo.countByTipoDocumentoAndNumDocumento(tipoDocumento, numDocumento);
  }
  
  Mono<Cliente> findFirstByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento,
      String numDocumento) {
    return clientesRepo.findFirstByTipoDocumentoAndNumDocumento(tipoDocumento, numDocumento);
  }
  
  Mono<Cliente> findFirstByIdAndIndEliminado(String id, Integer indEliminado) {
    return clientesRepo.findFirstByIdAndIndEliminado(id, indEliminado);
  }
  
  Mono<Long> countByIdAndIndEliminado(String id, Integer indEliminado) {
    return clientesRepo.countByIdAndIndEliminado(id, indEliminado);
  }
  
  

}
