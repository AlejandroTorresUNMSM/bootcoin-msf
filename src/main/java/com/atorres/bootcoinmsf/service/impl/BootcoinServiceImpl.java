package com.atorres.bootcoinmsf.service.impl;

import com.atorres.bootcoinmsf.client.FeignApiAccount;
import com.atorres.bootcoinmsf.exception.CustomException;
import com.atorres.bootcoinmsf.model.PaymentUpdate;
import com.atorres.bootcoinmsf.model.CreateRequest;
import com.atorres.bootcoinmsf.model.dao.BootcoinDao;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import com.atorres.bootcoinmsf.model.dto.SellerDto;
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
  private FeignApiAccount accountService;
  @Autowired
  private BootcoinRepository bootcoinRepository;
  @Autowired
  private BootcoinMapper bootcoinMapper;
  @Autowired
  private ReactiveHashOperations<String, String, BootcoinDao> hashOperations;
  private static final String KEY_REDIS = "bootcoin";

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
  public Mono<BootcoinDto> getBootcoin(String purseId) {
    return hashOperations.get(KEY_REDIS,purseId)
        .switchIfEmpty(this.checkBootcoinFromMongoRedis(purseId))
        .map(bootcoinMapper::toDto);
  }

  /**
   * Metodo para crear una billetera Bootcoin
   * @param request request
   * @return bootcoin
   */
  @Override
  public Mono<BootcoinDto> createBootcoin(CreateRequest request) {
    return validateWallet(request)
        .flatMap(pc -> bootcoinRepository.save(pc))
        .map(bootcoinMapper::toDto);
  }

  /**
   * Metodo para agregar metodo de pago
   * @param bootcoinId id bootcoin
   * @param paymentId id cuenta
   * @return bootcoin
   */
  @Override
  public Mono<BootcoinDto> addPayment(String bootcoinId,String paymentId) {
    return bootcoinRepository.findById(bootcoinId)
        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No se encontró la billetera")))
        .flatMap(bootcoinDao -> accountService.getAccount(paymentId).single()
            .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No existe la cuenta")))
            .map(accountDto -> {
              bootcoinDao.setPaymentId(accountDto.getId());
              return bootcoinDao;
            }))
        .flatMap(bootcoinDao -> bootcoinRepository.save(bootcoinDao))
        .doOnNext(bootcoinDao -> hashOperations.remove(KEY_REDIS,bootcoinDao.getId()))
        .map(bootcoinMapper::toDto);
  }

  @Override
  public Flux<SellerDto> getAllSeller() {
    return bootcoinRepository.findAll()
        .filter(bootcoinDao -> bootcoinDao.getIsSeller().equals(true))
        .map(bootcoinMapper::toSeller);
  }

  @Override
  public Mono<Void> deleteWallet(String bootcoinId) {
    return bootcoinRepository.findById(bootcoinId)
        .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No existe la billetera")))
        .flatMap(bootcoinDao -> bootcoinRepository.delete(bootcoinDao))
        .doOnSuccess(v -> hashOperations.remove(KEY_REDIS,bootcoinId));
  }

  /**
   * Valida si ya existe el bootcoin
   * @param request request
   * @return bootcoin
   */
  private Mono<BootcoinDao> validateWallet(CreateRequest request){
    return bootcoinRepository.findAll()
        .filter(pc -> pc.getPhone().equals(request.getPhone()))
        .any(pc -> true)
        .flatMap(exist -> {
          if (Boolean.TRUE.equals(exist)) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST,
                "Existe el phone en otra billetera"));
          } else {
            return Mono.just(bootcoinMapper.toDao(request));
          }
        });
  }

  /**
   * Metodo para obtener el bootcoin y setearlo al cache
   * @param bootcoinId bootcoin id
   * @return bootcoin
   */
  private Mono<BootcoinDao> checkBootcoinFromMongoRedis(String bootcoinId) {
    return bootcoinRepository.findById(bootcoinId)
        .switchIfEmpty(Mono
            .error(new CustomException(HttpStatus.NOT_FOUND, "No se encontró la billetera")))
        .flatMap(bootcoinDao -> {
          log.info("Guardando billetera en redis");
          return this.hashOperations.put(KEY_REDIS, bootcoinId, bootcoinDao).thenReturn(bootcoinDao);
        });
  }
}
