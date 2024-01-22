package br.com.webService.MongoDB.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.webService.MongoDB.demo.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
