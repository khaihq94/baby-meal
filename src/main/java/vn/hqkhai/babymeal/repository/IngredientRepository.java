package vn.hqkhai.babymeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.babymeal.entity.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {

}
