package vn.hqkhai.babymeal.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "recipe_ingredient")
public class RecipeIngredient {

	private List<Translation> units;
	private String ingredientId;
	
}
