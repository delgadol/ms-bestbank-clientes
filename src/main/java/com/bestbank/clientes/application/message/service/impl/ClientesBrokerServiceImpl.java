package com.bestbank.clientes.application.message.service.impl;


import com.bestbank.clientes.application.message.dto.ClienteBrokerReq;
import com.bestbank.clientes.application.message.dto.ClienteBrokerRes;
import com.bestbank.clientes.application.message.service.ClientesBrokerService;
import com.bestbank.clientes.application.services.ClienteService;
import com.bestbank.clientes.application.utils.BankFnUtils;
import com.bestbank.clientes.domain.model.Cliente;
import com.bestbank.clientes.domain.utils.TipoEstadoFinaciero;
import com.bestbank.clientes.infrastructure.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClientesBrokerServiceImpl implements ClientesBrokerService {
  
private final ClienteService servCliente;
  
  
  public ClientesBrokerServiceImpl(ClienteService servCliente) {
    this.servCliente = servCliente;
  }

  @Override
  public Mono<ClienteBrokerRes> postClienteBroker(ClienteBrokerReq cliente) {
    log.info(String.format("Registro de : %s", cliente.getNombres()));
    Cliente nuevoItem = ModelMapperUtils.map(cliente, Cliente.class);
    // Datos Complementarios //
    nuevoItem.setSecCtrl(BankFnUtils.uniqueProductCode());
    nuevoItem.setEstadoFinanciero(TipoEstadoFinaciero.SOLVENTE);
    nuevoItem.setIndEliminado(0);
    nuevoItem.setEstado("0");
    nuevoItem.setFechaRegistro(BankFnUtils.getDateTime());
    nuevoItem.setFechaModificacion(BankFnUtils.getDateTime());
    return servCliente
        .countByTipoDocumentoAndNumDocumento(cliente.getTipoDocumento(), cliente.getNumDocumento())
        .filter(contItem -> contItem > 0)
        .flatMap(cont -> 
        servCliente
              .findFirstByTipoDocumentoAndNumDocumento(
                  cliente.getTipoDocumento(), cliente.getNumDocumento())
              .map( clienteRes -> {
                ClienteBrokerRes clienteBrokerRes = 
                    ModelMapperUtils.map(clienteRes, ClienteBrokerRes.class);
                clienteBrokerRes.setCodCtrlBroker(cliente.getCodCtrlBroker());
                return clienteBrokerRes;
              })
        )
        .switchIfEmpty(
            servCliente.save(nuevoItem)
            .map( clienteRes -> {
              ClienteBrokerRes clienteBrokerRes = 
                  ModelMapperUtils.map(clienteRes, ClienteBrokerRes.class);
              clienteBrokerRes.setCodCtrlBroker(cliente.getCodCtrlBroker());
              return clienteBrokerRes;
            })
        );
  }

  @Override
  public Mono<ClienteBrokerRes> delClienteBroker(ClienteBrokerReq cliente) {
    return servCliente.findFirstByIdAndIndEliminado(cliente.getCodCliente(),0)
        .map( clienteRes -> {
          ClienteBrokerRes clienteBrokerRes = 
              ModelMapperUtils.map(clienteRes, ClienteBrokerRes.class);
          clienteBrokerRes.setCodCtrlBroker(cliente.getCodCtrlBroker());
          return clienteBrokerRes;
        })
        .switchIfEmpty(
            Mono.just(cliente)
            .map(item -> {
              ClienteBrokerRes clienteBrokerRes = 
                  ModelMapperUtils.map(item, ClienteBrokerRes.class);
              clienteBrokerRes.setId("-1");
              return clienteBrokerRes;
            })
        );
  }

}
