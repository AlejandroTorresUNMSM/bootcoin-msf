package com.atorres.bootcoinmsf.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Clase representa una peticion para comprar  bootcoins
 */
@Data
@Document("petition")
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
