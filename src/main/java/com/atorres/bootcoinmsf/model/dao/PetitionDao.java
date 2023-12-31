package com.atorres.bootcoinmsf.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Clase representa una peticion para comprar  bootcoins
 */
@Data
@Document("petitions")
public class PetitionDao {
  /**
   * Id
   */
  @Id
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
