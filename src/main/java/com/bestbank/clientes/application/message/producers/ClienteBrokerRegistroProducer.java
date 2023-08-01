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

    String[] idCtrlChanel = cliente.getCodCtrlBroker().split(":");
    String kafKaChanel = KAFKA_TOPICO;
    if (idCtrlChanel.length>1) {
      cliente.setCodCtrlBroker(idCtrlChanel[0]);
      kafKaChanel = idCtrlChanel[1];
    }
    String jsonCliente = JsonUtils.objectToJson(cliente);
    if (jsonCliente.isEmpty()) {
      return;
    }
    log.info("Enviado Notificacio Cliente Nuevo");
    final String kafkaTopic = kafKaChanel;
    log.info(String.format("enviando a Topico : %s", kafKaChanel));
    this.kafkaTemplate.send(kafkaTopic,jsonCliente);
  }

}
