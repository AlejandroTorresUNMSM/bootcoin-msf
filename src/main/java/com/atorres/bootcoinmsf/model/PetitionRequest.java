package com.atorres.bootcoinmsf.model;

import lombok.Data;

@Data
public class PetitionRequest {
  /**
   * Id de la billetera de comprador
   */
  private String pursecoinId;
  /**
   * Clave de seguridad
   */
  private String pass;
}
