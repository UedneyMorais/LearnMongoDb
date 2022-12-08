package com.learnmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learnmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
