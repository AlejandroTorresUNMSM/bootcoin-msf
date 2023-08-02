package com.atorres.bootcoinmsf.service.strategyPayment;

import reactor.core.publisher.Mono;

public interface PaymentStrategy {
  Mono<Boolean> verifyPayment();
}
