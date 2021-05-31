package vn.hqkhai.babymeal.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ingredient")
public class Ingredient {

	@Id
	private String id;
	private List<Translation> name; 
	
}
