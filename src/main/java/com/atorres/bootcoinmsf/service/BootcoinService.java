package com.atorres.bootcoinmsf.service;
import com.atorres.bootcoinmsf.model.PaymentUpdate;
import com.atorres.bootcoinmsf.model.CreateRequest;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import com.atorres.bootcoinmsf.model.dto.SellerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {
  Flux<BootcoinDto> getAll();
  Mono<BootcoinDto> getBootcoin(String purseId);

  Mono<BootcoinDto> createBootcoin(CreateRequest request);

  Mono<BootcoinDto> addPayment(String bootcoinId,String paymentId);

  Flux<SellerDto> getAllSeller();

  Mono<Void> deleteWallet(String bootcoinId);

}
