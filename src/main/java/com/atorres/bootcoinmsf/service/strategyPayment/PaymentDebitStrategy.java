package com.atorres.bootcoinmsf.service.strategyPayment;

import reactor.core.publisher.Mono;

public class PaymentDebitStrategy implements  PaymentStrategy{
  @Override
  public Mono<Boolean> verifyPayment() {
    return null;
  }
}
