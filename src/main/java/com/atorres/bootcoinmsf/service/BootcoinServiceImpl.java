package com.atorres.bootcoinmsf.service;

import com.atorres.bootcoinmsf.exception.CustomException;
import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dto.PursecoinDto;
import com.atorres.bootcoinmsf.repository.PursecoinRepository;
import com.atorres.bootcoinmsf.utils.BootcoinMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public class BootcoinServiceImpl implements BootcoinService{
  @Autowired
  private PursecoinRepository pursecoinRepository;
  @Autowired
  private BootcoinMapper bootcoinMapper;

  @Override
  public Flux<PursecoinDto> getAll() {
    return pursecoinRepository.findAll()
        .map(bootcoinMapper::toDto);
  }

  @Override
  public Mono<PursecoinDto> getPursecoin(String purseId) {
    return pursecoinRepository.findById(purseId)
        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Ya existe el nroDocumento")))
        .map(bootcoinMapper::toDto);
  }
}
