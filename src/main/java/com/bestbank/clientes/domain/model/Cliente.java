package com.bestbank.clientes.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;

import lombok.Data;

@Data
@Document("personas")
public class Cliente  implements Serializable {
  
  private static final long serialVersionUID = 3816734689501931947L;

  @Id
  private String id;
  
  private String secCtrl;
  
  private TipoCliente tipoCliente;
  
  private TipoDocumento tipoDocumento;
  
  private String numDocumento;
  
  private String nombres;
  
  private String apellidos;
  
  private String estado;
  
  private Integer indEliminado;
  
  private Date fechaRegistro;
  
  private Date fechaModificacion;

}