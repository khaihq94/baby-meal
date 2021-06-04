package vn.hqkhai.urfuture.api.v0.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.urfuture.openapi.api.IngredientsApi;
import vn.hqkhai.urfuture.openapi.model.IngredientModel;
import vn.hqkhai.urfuture.service.IngredientService;

@RestController
public class IngredientApiController implements IngredientsApi {

	private IngredientService ingredientService;
	
	public IngredientApiController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@Override
	public ResponseEntity<IngredientModel> createIngredient(IngredientModel ingredientModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.createIngredient(ingredientModel));
	}
	
	@Override
	public ResponseEntity<List<IngredientModel>> getAllIngredients() {
		return ResponseEntity.ok(ingredientService.getAllIngredients());
	}
	
	@Override
	public ResponseEntity<IngredientModel> findIngredientById(String ingredientId) {
		return ResponseEntity.ok(ingredientService.findIngredientById(ingredientId));
	}
	
	@Override
	public ResponseEntity<IngredientModel> updateIngredient(String ingredientId, IngredientModel ingredientModel) {
		return ResponseEntity.ok(ingredientService.updateIngredient(ingredientId, ingredientModel));
	}
	
	@Override
	public ResponseEntity<Void> deleteIngredient(String ingredientId) {
		ingredientService.deleteIngredient(ingredientId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
