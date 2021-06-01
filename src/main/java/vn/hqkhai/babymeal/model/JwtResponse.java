package vn.hqkhai.babymeal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 7681825536620267239L;

	private final String token;
	
}
