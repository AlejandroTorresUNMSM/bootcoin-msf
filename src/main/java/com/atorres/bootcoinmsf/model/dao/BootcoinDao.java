package com.atorres.bootcoinmsf.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Clase Billetera bootcoin
 */
@Data
@Document("bootcoins")
public class BootcoinDao {
  /**
   * Id
   */
  @Id
  private String id;
  /**
   * Nombre del usuario
   */
  private String nombre;
  /**
   * Numero de celular
   */
  private String phone;
  /**
   * Correo
   */
  private String email;
  /**
   * Cantidad de bootcoin
   */
  private BigDecimal bootcoinAmount;
  /**
   * Boolean indica si es vendedor
   */
  private Boolean isSeller;
  /**
   * Clave seguridad
   */
  private String pass;
}
