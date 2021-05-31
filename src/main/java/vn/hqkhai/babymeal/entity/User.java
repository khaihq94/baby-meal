package vn.hqkhai.babymeal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private String username;
	private String password;
	
}
