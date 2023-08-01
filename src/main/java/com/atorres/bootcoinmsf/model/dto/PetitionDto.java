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
   * Nombre del vendedor
   */
  private String nameSeller;
  /**
   * Nombre del comprador
   */
  private String namePurchaser;
  /**
   * Metodo de transferencia : cuenta o yanki
   */
  private String methodTransfer;
  /**
   * Id del metodo de transferencia
   */
  private String transferProduct;
  /**
   * Cantidad de bootcoin  a transferir
   */
  private BigDecimal amountcoin;
}
