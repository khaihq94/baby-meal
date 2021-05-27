package vn.hqkhai.babymeal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "translation")
public class Translation {

	private String language;
	private String content;
	
}
