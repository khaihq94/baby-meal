package vn.hqkhai.urfuture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.urfuture.entity.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

}
