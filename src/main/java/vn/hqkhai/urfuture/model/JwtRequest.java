package vn.hqkhai.urfuture.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 8924328534963126641L;
	
	private String username;
	private String password;

}
