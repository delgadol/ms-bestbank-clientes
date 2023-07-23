package com.bestbank.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsBestbankClientesApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBestbankClientesApplication.class, args);
  }

}
