package vn.hqkhai.babymeal.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.hqkhai.babymeal.entity.Dish;
import vn.hqkhai.babymeal.entity.Translation;
import vn.hqkhai.babymeal.exception.ObjectNotFoundException;
import vn.hqkhai.babymeal.openapi.model.DishModel;
import vn.hqkhai.babymeal.repository.DishRepository;
import vn.hqkhai.babymeal.utils.ModelMapperUtils;

@Service
public class DishService {

	private DishRepository repository;
	
	public DishService(DishRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public List<DishModel> getAllDishes() {
		return ModelMapperUtils.mapList(repository.findAll(), DishModel.class);
	}

	@Transactional
	public DishModel createDish(DishModel dishModel) {
		Dish dish = new Dish();
		dish.setId(UUID.randomUUID().toString());
		dish.setNames(ModelMapperUtils.mapList(dishModel.getNames(), Translation.class));
		return ModelMapperUtils.mapObject(repository.insert(dish), DishModel.class);
	}

	@Transactional
	public DishModel findDishById(String dishId) {
		Optional<Dish> dishOptional = repository.findById(dishId);
		if(!dishOptional.isPresent()) {
			throw new ObjectNotFoundException("DishId");
		}
		return ModelMapperUtils.mapObject(dishOptional.get(), DishModel.class);
	}

	@Transactional
	public DishModel updateDish(String dishId, DishModel dishModel) {
		Optional<Dish> dishOptional = repository.findById(dishId);
		if(!dishOptional.isPresent()) {
			throw new ObjectNotFoundException("DishId");
		}
		Dish dish = dishOptional.get();
		dish.setNames(ModelMapperUtils.mapList(dishModel.getNames(), Translation.class));
		return ModelMapperUtils.mapObject(repository.save(dish), DishModel.class);
	}
	
	@Transactional
	public void deleteDish(String dishId) {
		repository.deleteById(dishId);
	}
	
}
