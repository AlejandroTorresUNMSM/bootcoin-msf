package com.atorres.bootcoinmsf.service;

import com.atorres.bootcoinmsf.exception.CustomException;
import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dao.PursecoinDao;
import com.atorres.bootcoinmsf.model.dto.PursecoinDto;
import com.atorres.bootcoinmsf.repository.PursecoinRepository;
import com.atorres.bootcoinmsf.utils.BootcoinMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
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
  @Autowired
  private ReactiveHashOperations<String, String, PursecoinDao> hashOperations;
  private static final String KEY_REDIS = "pursecoin";

  @Override
  public Flux<PursecoinDto> getAll() {
    return pursecoinRepository.findAll()
        .map(bootcoinMapper::toDto);
  }

  @Override
  public Mono<PursecoinDto> getPursecoin(String purseId) {
    return hashOperations.get(KEY_REDIS,purseId)
        .switchIfEmpty(this.checkPursecoinFromMongoRedis(purseId))
        .map(bootcoinMapper::toDto);
  }

  @Override
  public Mono<PursecoinDto> createPursecoin(PursecoinRequest request) {
    return validatePurse(request)
        .flatMap(pc -> pursecoinRepository.save(pc))
        .map(bootcoinMapper::toDto);
  }


  private Mono<PursecoinDao> validatePurse(PursecoinRequest request){
    return pursecoinRepository.findAll()
        .filter(pc -> pc.getPhone().equals(request.getPhone()))
        .any(pc -> true)
        .flatMap(exist -> {
          if (Boolean.TRUE.equals(exist)) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST,
                "Existe el nroDocumento o phone"));
          } else {
            return Mono.just(bootcoinMapper.toDao(request));
          }
        });
  }

  private Mono<PursecoinDao> checkPursecoinFromMongoRedis(String purseId) {
    return pursecoinRepository.findById(purseId)
        .switchIfEmpty(Mono
            .error(new CustomException(HttpStatus.NOT_FOUND, "No se encontró la billetera")))
        .flatMap(pursecoinDao -> {
          log.info("Guardando billetera en redis");
          return this.hashOperations.put(KEY_REDIS, purseId, pursecoinDao).thenReturn(pursecoinDao);
        });
  }
}
