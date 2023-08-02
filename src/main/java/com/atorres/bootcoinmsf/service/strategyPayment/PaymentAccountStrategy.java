package com.atorres.bootcoinmsf.service.strategyPayment;

import reactor.core.publisher.Mono;

public class PaymentAccountStrategy implements  PaymentStrategy{
  @Override
  public Mono<Boolean> verifyPayment() {
    return null;
  }
}
