package vn.hqkhai.babymeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.babymeal.entity.Age;

public interface AgeRepository extends MongoRepository<Age, String> {

}
