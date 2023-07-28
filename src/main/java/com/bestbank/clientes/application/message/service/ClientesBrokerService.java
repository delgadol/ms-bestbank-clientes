package com.bestbank.clientes.application.message.service;

import com.bestbank.clientes.application.message.dto.ClienteBrokerReq;
import com.bestbank.clientes.application.message.dto.ClienteBrokerRes;
import reactor.core.publisher.Mono;

public interface ClientesBrokerService {

    Mono<ClienteBrokerRes> postClienteBroker(ClienteBrokerReq cliente);
    
    Mono<ClienteBrokerRes> delClienteBroker(ClienteBrokerReq cliente);
    
    
}
