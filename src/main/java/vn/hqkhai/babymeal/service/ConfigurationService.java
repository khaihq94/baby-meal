package vn.hqkhai.babymeal.service;

import org.springframework.stereotype.Service;

import vn.hqkhai.babymeal.enums.SupportedLanguages;
import vn.hqkhai.babymeal.openapi.model.ConfigurationModel;

@Service
public class ConfigurationService {

	private DishService dishService;
	private IngredientService ingredientService;
	private AgeService ageService;
	
	public ConfigurationService(DishService dishService, IngredientService ingredientService, AgeService ageService) {
		this.dishService = dishService;
		this.ingredientService = ingredientService;
		this.ageService = ageService;
	}
	
	public ConfigurationModel getConfigurationData() {
		ConfigurationModel configurationResponse = new ConfigurationModel();
		configurationResponse.setAges(ageService.getAllAges());
		configurationResponse.setDishes(dishService.getAllDishes());
		configurationResponse.setIngredients(ingredientService.getAllIngredients());
		configurationResponse.setSupportedLanguages(SupportedLanguages.getAllIds());
		return configurationResponse;
	}

}
