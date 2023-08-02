package com.atorres.bootcoinmsf.service.impl;

import com.atorres.bootcoinmsf.exception.CustomException;
import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dao.BootcoinDao;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import com.atorres.bootcoinmsf.repository.BootcoinRepository;
import com.atorres.bootcoinmsf.service.BootcoinService;
import com.atorres.bootcoinmsf.utils.BootcoinMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public class BootcoinServiceImpl implements BootcoinService {
  @Autowired
  private BootcoinRepository bootcoinRepository;
  @Autowired
  private BootcoinMapper bootcoinMapper;
  @Autowired
  private ReactiveHashOperations<String, String, BootcoinDao> hashOperations;
  private static final String KEY_REDIS = "pursecoin";

  /**
   * Traer todos las billeteras bootcoin
   * @return lista pursecoinDto
   */
  @Override
  public Flux<BootcoinDto> getAll() {
    return bootcoinRepository.findAll()
        .map(bootcoinMapper::toDto);
  }

  /**
   * Trae una billetera por su id
   * @param purseId id
   * @return pursecoin
   */
  @Override
  public Mono<BootcoinDto> getPursecoin(String purseId) {
    return hashOperations.get(KEY_REDIS,purseId)
        .switchIfEmpty(this.checkPursecoinFromMongoRedis(purseId))
        .map(bootcoinMapper::toDto);
  }

  @Override
  public Mono<BootcoinDto> createPursecoin(PursecoinRequest request) {
    return validatePurse(request)
        .flatMap(pc -> bootcoinRepository.save(pc))
        .map(bootcoinMapper::toDto);
  }


  private Mono<BootcoinDao> validatePurse(PursecoinRequest request){
    return bootcoinRepository.findAll()
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

  private Mono<BootcoinDao> checkPursecoinFromMongoRedis(String purseId) {
    return bootcoinRepository.findById(purseId)
        .switchIfEmpty(Mono
            .error(new CustomException(HttpStatus.NOT_FOUND, "No se encontró la billetera")))
        .flatMap(bootcoinDao -> {
          log.info("Guardando billetera en redis");
          return this.hashOperations.put(KEY_REDIS, purseId, bootcoinDao).thenReturn(bootcoinDao);
        });
  }
}
