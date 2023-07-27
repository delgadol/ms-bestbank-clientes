package com.bestbank.clientes.application.message.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductoResgitroProducer {
  
private static final String KAFKA_ACT_REG_PROD = "yanki-action-reg-prod";
  
  @Autowired
  private KafkaTemplate<String,String> kafkaTemplate;
  
  public void sendProdRegReq() {
   log.info("Informacion");
  }
  
  

}
