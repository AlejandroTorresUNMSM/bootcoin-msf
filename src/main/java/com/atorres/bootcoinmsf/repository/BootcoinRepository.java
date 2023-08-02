package com.atorres.bootcoinmsf.repository;

import com.atorres.bootcoinmsf.model.dao.BootcoinDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BootcoinRepository extends ReactiveMongoRepository<BootcoinDao, String> {
  @Query("{ 'isSeller': true }")
  Flux<BootcoinDao> findAllSellers();

  Mono<BootcoinDao> findByPhone(String phone);
}
