package com.atorres.bootcoinmsf.utils;

import java.math.BigDecimal;

public enum TasaEnum {
  COMPRA("COMPRA",new BigDecimal("4.2")),
  VENTA("VENTA",new BigDecimal("2.2"));
  private final String key;
  private final BigDecimal value;

  TasaEnum(String key,BigDecimal value){
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public BigDecimal getValue() {
    return value;
  }

  public static BigDecimal getValueByKey(String key) {
    for (TasaEnum keyValue : TasaEnum.values()) {
      if (keyValue.getKey().equals(key)) {
        return keyValue.getValue();
      }
    }
    return new BigDecimal("0.0");
  }
}
