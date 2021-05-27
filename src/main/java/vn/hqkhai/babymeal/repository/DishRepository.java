package vn.hqkhai.babymeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.babymeal.entity.Dish;

public interface DishRepository extends MongoRepository<Dish, String> {

}
