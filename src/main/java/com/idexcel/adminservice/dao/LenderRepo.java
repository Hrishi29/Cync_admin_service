package com.idexcel.adminservice.dao;

import com.idexcel.adminservice.entity.Lender;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LenderRepo extends MongoRepository {

    Optional<Lender> findByName(String name);
}
