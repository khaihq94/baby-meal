package vn.hqkhai.urfuture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.urfuture.entity.Dish;

public interface DishRepository extends MongoRepository<Dish, String> {

}
