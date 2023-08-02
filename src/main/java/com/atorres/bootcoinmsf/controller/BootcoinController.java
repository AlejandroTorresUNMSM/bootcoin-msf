package com.atorres.bootcoinmsf.controller;

import com.atorres.bootcoinmsf.model.PursecoinRequest;
import com.atorres.bootcoinmsf.model.dto.BootcoinDto;
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


  @GetMapping(path = {"","/"}, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<BootcoinDto> getAllPurse(){
    return bootcoinService.getAll();
  }

  @GetMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<BootcoinDto> getPurseById(@PathVariable String id){
    return bootcoinService.getPursecoin(id);
  }

  @PostMapping(value = "/create", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<BootcoinDto> create(@RequestBody PursecoinRequest request){
    return bootcoinService.createPursecoin(request);
  }
}
