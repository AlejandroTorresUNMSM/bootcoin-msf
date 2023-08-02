package com.atorres.bootcoinmsf.model;

import com.atorres.bootcoinmsf.utils.PaymentType;
import lombok.Data;

@Data
public class CreateRequest {
  private String nombre;
  private String phone;
  private String email;
  private String pass;
}
