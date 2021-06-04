package vn.hqkhai.urfuture.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.hqkhai.urfuture.entity.Dish;
import vn.hqkhai.urfuture.entity.Translation;
import vn.hqkhai.urfuture.exception.ObjectNotFoundException;
import vn.hqkhai.urfuture.openapi.model.DishModel;
import vn.hqkhai.urfuture.repository.DishRepository;
import vn.hqkhai.urfuture.utils.ModelMapperUtils;

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
		dish.setName(ModelMapperUtils.mapList(dishModel.getName(), Translation.class));
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
		dish.setName(ModelMapperUtils.mapList(dishModel.getName(), Translation.class));
		return ModelMapperUtils.mapObject(repository.save(dish), DishModel.class);
	}
	
	@Transactional
	public void deleteDish(String dishId) {
		repository.deleteById(dishId);
	}
	
}
