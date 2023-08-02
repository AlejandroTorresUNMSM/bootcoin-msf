package com.atorres.bootcoinmsf.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BootcoinDto {
  private String id;
  private String nombre;
  private String phone;
  private String email;
  private BigDecimal bootcoinAmount;
  private String pass;
}
