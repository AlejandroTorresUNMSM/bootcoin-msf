package com.atorres.bootcoinmsf.service;
import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {
  Flux<BootcoinDto> getAll();
  Mono<BootcoinDto> getPursecoin(String purseId);

  Mono<BootcoinDto> createPursecoin(PursecoinRequest request);

}
