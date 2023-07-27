package com.bestbank.clientes.application.message.dto;

import com.bestbank.clientes.domain.utils.TipoCliente;
import com.bestbank.clientes.domain.utils.TipoDocumento;
import com.bestbank.clientes.domain.utils.TipoEstadoFinaciero;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Clase que representa a un cliente y que implementa la interfaz Serializable.
 */

@Data
public class ClienteInfoKafka  implements Serializable {
  
  private static final long serialVersionUID = 3816734689501931947L;

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
  
  private String numeroTelefono;
  
  private String imeiTelefono;
  
  private String emailPersona;

}