package com.bestbank.clientes.application.message.producers;

import com.bestbank.clientes.application.message.dto.NotificacionInfoKafka;
import com.bestbank.clientes.application.utils.BankFnUtils;
import com.bestbank.clientes.application.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacionesProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  
  private static final String KAFKA_NOTIFICACIONES = "topic-movil-notif";
  
  
  public void sendNotification(String msgNotificacion) {
    NotificacionInfoKafka notificacion = 
        new NotificacionInfoKafka(BankFnUtils.uniqueProductCode(), msgNotificacion);
    String jsonNotificacion = JsonUtils.objectToJson(notificacion);
    log.info("Enviando Notificacion");
    this.kafkaTemplate.send(KAFKA_NOTIFICACIONES, jsonNotificacion);    
  }
  
  
}
