package com.bestbank.clientes.application.message.consumer;

import com.bestbank.clientes.application.ClientesApplication;
import com.bestbank.clientes.application.message.dto.ClienteInfoKafka;
import com.bestbank.clientes.application.message.producers.ClienteRegistroProducer;
import com.bestbank.clientes.application.message.producers.NotificacionesProducer;
import com.bestbank.clientes.application.utils.JsonUtils;
import com.bestbank.clientes.infrastructure.utils.ModelMapperUtils;
import com.bestbank.clientes.presentation.dto.ClienteReq;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientesRegistroConsumer {
  
  @Autowired
  private ClientesApplication servApp;
  
  @Autowired
  private NotificacionesProducer servNot;
  
  @Autowired
  private ClienteRegistroProducer servRegNot;
  
  @KafkaListener(topics = "topic-registra-cliente", groupId = "group_id")
  public void consumerClientRegister(String jsonClienteReqInfo) {
    final ClienteInfoKafka clienteInfo = JsonUtils.jsonToObject(
        jsonClienteReqInfo, ClienteInfoKafka.class);
    if (!Objects.isNull(clienteInfo)) {
      ClienteReq nuevoCliente = ModelMapperUtils.map(clienteInfo, ClienteReq.class);
      servApp.postClient(nuevoCliente)
      .subscribe(item -> {
        ClienteInfoKafka clienteInfoRes = ModelMapperUtils.map(clienteInfo,  ClienteInfoKafka.class);
        clienteInfoRes.setId(item.getId());
        servRegNot.sendRegistroClientOk(clienteInfo);
        servNot.sendNotification(JsonUtils.objectToJson(clienteInfoRes));
      });
    } else {
      servNot.sendNotification(JsonUtils.objectToJson("No se Puede Procesar Solicitud"));
    }
    
  }

}
