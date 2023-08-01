package com.atorres.bootcoinmsf.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PursecoinDto {
  private String id;
  private String nombre;
  private String phone;
  private String email;
  private BigDecimal bootcoin;
  private String pass;
}
