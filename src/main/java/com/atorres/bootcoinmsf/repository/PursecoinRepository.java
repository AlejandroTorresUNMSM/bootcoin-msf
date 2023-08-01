package com.atorres.bootcoinmsf.repository;

import com.atorres.bootcoinmsf.model.dao.PursecoinDao;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PursecoinRepository extends ReactiveMongoRepository<PursecoinDao, String> {
  @Query("{ 'isSeller': true }")
  Flux<PursecoinDao> findAllSellers();
}
