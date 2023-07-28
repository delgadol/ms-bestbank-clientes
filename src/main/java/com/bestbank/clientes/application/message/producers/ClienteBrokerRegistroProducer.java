package com.bestbank.clientes.application.message.producers;

import com.bestbank.clientes.application.message.dto.ClienteBrokerRes;
import com.bestbank.clientes.application.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteBrokerRegistroProducer {
  
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  
  private static final String KAFKA_TOPICO = "clientes-registrado";
  
  public void sendRegistroClientResp(ClienteBrokerRes cliente) {
    
    String jsonCliente = JsonUtils.objectToJson(cliente);
    
    if (jsonCliente.isEmpty()) {
      return;
    }
    log.info("Enviado Notificacio Cliente Nuevo");
    this.kafkaTemplate.send(KAFKA_TOPICO,jsonCliente);
  }

}
