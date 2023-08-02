package com.atorres.bootcoinmsf.controller;

import com.atorres.bootcoinmsf.model.PaymentUpdate;
import com.atorres.bootcoinmsf.model.CreateRequest;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
import com.atorres.bootcoinmsf.model.dto.SellerDto;
import com.atorres.bootcoinmsf.service.BootcoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcoin")
@Slf4j
public class BootcoinController {
  @Autowired
  private BootcoinService bootcoinService;

  /**
   * Metodo que trae todos las billeteras
   * @return lista bootcoins
   */
  @GetMapping(path = {"","/"}, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<BootcoinDto> getAllBootcoind(){
    return bootcoinService.getAll();
  }

  /**.
   * Metodo que trae un bootcoin por id
   * @param id id
   * @return bootcoinDto
   */
  @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<BootcoinDto> getBootcoinById(@PathVariable String id){
    return bootcoinService.getBootcoin(id);
  }

  /**
   * Metodo para crear un bootcoin
   * @param request request
   * @return bootcoinDto
   */
  @PostMapping(value = "/create", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<BootcoinDto> create(@RequestBody CreateRequest request){
    return bootcoinService.createBootcoin(request);
  }

  /**
   * Metodo que actualiza el id del metodo de pago
   * @param bootcoinId bootcoin id
   * @param paymentId id de la cuenta
   * @return bootcoinDto
   */
  @PatchMapping(value = "/add-payment/{bootcoinId/paymentId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<BootcoinDto> addPayment(
      @PathVariable String bootcoinId,
      @PathVariable String paymentId){
    return bootcoinService.addPayment(bootcoinId,paymentId);
  }

  /**
   * Metodo que trae todos los vendedores
   * @return lista vendedores
   */
  @GetMapping(value = "/sellers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<SellerDto> getAllSeller(){
    return bootcoinService.getAllSeller();
  }

  /**
   * Metodo para eliminar una billetera bootcoin
   * @param bootcoinId bootcoin id
   * @return void
   */
  @DeleteMapping(value = "/{bootcoinId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<Void> delete(@PathVariable String bootcoinId){
    return bootcoinService.deleteWallet(bootcoinId);
  }

}
