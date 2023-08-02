package com.atorres.bootcoinmsf.service.strategyPayment;

import com.atorres.bootcoinmsf.utils.PaymentType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class PaymentStrategyFactory {
  private final Map<PaymentType, PaymentStrategy> strategies = new EnumMap<>(PaymentType.class);

  public PaymentStrategyFactory() {
    initStrategies();
  }

  public PaymentStrategy getStrategy(PaymentType paymentType) {
    if (paymentType == null || !strategies.containsKey(paymentType)) {
      throw new IllegalArgumentException("Invalid " + paymentType);
    }
    return strategies.get(paymentType);
  }

  private void initStrategies() {
    strategies.put(PaymentType.ACCOUNT, new PaymentAccountStrategy());
    strategies.put(PaymentType.DEBIT, new PaymentDebitStrategy());
  }
}
