package com.bestbank.clientes.domain.model;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;
import com.bestbank.clientes.domain.utils.TipoEstadoFinaciero;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa a un cliente y que implementa la interfaz Serializable.
 */
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
  
  private TipoEstadoFinaciero estadoFinanciero;
  
  private Integer indEliminado;
  
  private Integer indMonedero;
  
  private Date fechaRegistro;
  
  private Date fechaModificacion;

}