package com.atorres.bootcoinmsf.repository;

import com.atorres.bootcoinmsf.model.dao.PursecoinDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PursecoinRepository extends ReactiveMongoRepository<PursecoinDao, String> {
}
