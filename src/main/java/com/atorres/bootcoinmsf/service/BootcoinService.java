package com.atorres.bootcoinmsf.service;
import com.atorres.bootcoinmsf.model.dto.PursecoinDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {
  Flux<PursecoinDto> getAll();
  Mono<PursecoinDto> getPursecoin(String purseId);

}
