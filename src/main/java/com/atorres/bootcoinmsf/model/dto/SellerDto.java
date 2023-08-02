package com.atorres.bootcoinmsf.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellerDto {
  private String nombre;
  private String phone;
  private BigDecimal bootcoinAmount;
}
