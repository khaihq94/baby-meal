package vn.hqkhai.babymeal.api.v0;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.babymeal.openapi.api.DishesApi;
import vn.hqkhai.babymeal.openapi.model.DishModel;
import vn.hqkhai.babymeal.service.DishService;

@RestController
public class DishesApiController implements DishesApi {

	private DishService dishService;
	
	public DishesApiController(DishService dishService) {
		this.dishService = dishService;
	}
	
	@Override
	public ResponseEntity<DishModel> createDish(DishModel dishModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dishService.createDish(dishModel));
	}
	
	@Override
	public ResponseEntity<List<DishModel>> getAllDishes() {
		return ResponseEntity.ok(dishService.getAllDishes());
	}
	
	@Override
	public ResponseEntity<DishModel> findDishById(String dishId) {
		return ResponseEntity.ok(dishService.findDishById(dishId));
	}
	
	@Override
	public ResponseEntity<DishModel> updateDish(String dishId, DishModel dishModel) {
		return ResponseEntity.ok(dishService.updateDish(dishId, dishModel));
	}
	
	@Override
	public ResponseEntity<Void> deleteDish(String dishId) {
		dishService.deleteDish(dishId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
