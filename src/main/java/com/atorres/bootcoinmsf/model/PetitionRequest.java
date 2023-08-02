package com.atorres.bootcoinmsf.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PetitionRequest {
  /**
   * Phone de vendedor
   */
  private String phoneSeller;
  /**
   * Cantidad bootcoin a comprar
   */
  private BigDecimal bootcoinAmout;
}
