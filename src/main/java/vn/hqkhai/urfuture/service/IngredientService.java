package vn.hqkhai.urfuture.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.hqkhai.urfuture.entity.Ingredient;
import vn.hqkhai.urfuture.entity.Translation;
import vn.hqkhai.urfuture.exception.ObjectNotFoundException;
import vn.hqkhai.urfuture.openapi.model.IngredientModel;
import vn.hqkhai.urfuture.repository.IngredientRepository;
import vn.hqkhai.urfuture.utils.ModelMapperUtils;

@Service
public class IngredientService {

	private IngredientRepository repository;
	
	public IngredientService(IngredientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public List<IngredientModel> getAllIngredients() {
		return ModelMapperUtils.mapList(repository.findAll(), IngredientModel.class);
	}

	@Transactional
	public IngredientModel createIngredient(IngredientModel ingredientModel) {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(UUID.randomUUID().toString());
		ingredient.setName(ModelMapperUtils.mapList(ingredientModel.getName(), Translation.class));
		return ModelMapperUtils.mapObject(repository.insert(ingredient), IngredientModel.class);
	}

	@Transactional
	public IngredientModel findIngredientById(String ingredientId) {
		Optional<Ingredient> ingredientOptional = repository.findById(ingredientId);
		if(!ingredientOptional.isPresent()) {
			throw new ObjectNotFoundException("ingredientId");
		}
		return ModelMapperUtils.mapObject(ingredientOptional.get(), IngredientModel.class);
	}

	@Transactional
	public IngredientModel updateIngredient(String ingredientId, IngredientModel ingredientModel) {
		Optional<Ingredient> ingredientOptional = repository.findById(ingredientId);
		if(!ingredientOptional.isPresent()) {
			throw new ObjectNotFoundException("ingredientId");
		}
		Ingredient ingredient = ingredientOptional.get();
		ingredient.setName(ModelMapperUtils.mapList(ingredientModel.getName(), Translation.class));
		return ModelMapperUtils.mapObject(repository.save(ingredient), IngredientModel.class);
	}
	
	@Transactional
	public void deleteIngredient(String ingredientId) {
		repository.deleteById(ingredientId);
	}
	
}
