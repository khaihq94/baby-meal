package vn.hqkhai.babymeal.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "recipe")
public class Recipe {
	
	@Id
	private String id;
	private List<Translation> names;
	private String ageId;
	private List<RecipeIngredient> recipeIngredients;
	private List<String> suggestionRecipesId;
	private String dishId;
	private String mainImageId;
	private List<String> imagesId;
	private List<Translation> preparationTime;
	private String videoUrl;
	private List<Translation> sources;
	private List<Translation> notes;

}
