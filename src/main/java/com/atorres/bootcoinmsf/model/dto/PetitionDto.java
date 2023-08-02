package com.atorres.bootcoinmsf.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PetitionDto {
  /**
   * Id
   */
  private String id;
  /**
   * Celular del vendedor
   */
  private String phone;
  /**
   * Nombre del comprador
   */
  private String nameBuyer;
  /**
   * Nombre del vendedor
   */
  private String nameSeller;
  /**
   * Metodo de transferencia : cuenta o yanki
   */
  private String methodTransfer;
  /**
   * Id cuenta comprador
   */
  private String accountBuyer;
  /**
   * Id cuenta vendedor
   */
  private String accountSeller;
  /**
   * Cantidad de bootcoin  a transferir
   */
  private BigDecimal amountcoin;
}
