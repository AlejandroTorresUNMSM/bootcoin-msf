package com.atorres.bootcoinmsf.model;

import lombok.Data;

/**
 * Request para agregar saldo a la billetera
 */
@Data
public class RequestAddAmount {
  /**
   * Cantidad de bootcoin a comprar
   */
  private String amountCoin;
}
