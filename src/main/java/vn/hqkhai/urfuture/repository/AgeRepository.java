package vn.hqkhai.urfuture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.urfuture.entity.Age;

public interface AgeRepository extends MongoRepository<Age, String> {

}
