package com.bestbank.clientes.application.message.consumer;

import com.bestbank.clientes.application.message.dto.ClienteBrokerReq;
import com.bestbank.clientes.application.message.producers.ClienteBrokerRegistroProducer;
import com.bestbank.clientes.application.message.producers.NotificacionesProducer;
import com.bestbank.clientes.application.message.service.ClientesBrokerService;
import com.bestbank.clientes.application.utils.JsonUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientesBrokerRegistroConsumer {
  
  @Autowired
  private ClientesBrokerService servApp;
  
  @Autowired
  private NotificacionesProducer servNot;
  
  @Autowired
  private ClienteBrokerRegistroProducer servRegNot;
  
  @KafkaListener(topics = "clientes-registrar", groupId = "group_id")
  public void consumerClientRegister(String jsonClienteBrokerReq) {
    final ClienteBrokerReq clienteBrokerReq = JsonUtils.jsonToObject(
        jsonClienteBrokerReq, ClienteBrokerReq.class);
    if (!Objects.isNull(clienteBrokerReq)) {
      servApp.postClienteBroker(clienteBrokerReq)
      .subscribe(item -> {
        //  Kafka Registro OK
        servRegNot.sendRegistroClientResp(item);
        // Kafka Notificaicones
        servNot.sendNotification(item);
      });
    } else {
      Map<String,String> notificionData = new HashMap<>();
      notificionData.put("msg", "No se puede Procesar Registro" );
      notificionData.put("data", jsonClienteBrokerReq );
      servNot.sendNotification(notificionData);
    }
    
  }

}
