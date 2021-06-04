package vn.hqkhai.urfuture.api.v0.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hqkhai.urfuture.constants.RequestConstants;
import vn.hqkhai.urfuture.model.JwtRequest;
import vn.hqkhai.urfuture.model.JwtResponse;
import vn.hqkhai.urfuture.model.UserDTO;
import vn.hqkhai.urfuture.service.JwtUserDetailsService;
import vn.hqkhai.urfuture.utils.JwtTokenUtils;

@RestController
public class JwtAuthenticationController {

	private AuthenticationManager authenticationManager;
	private JwtTokenUtils jwtTokenUtils;
	private JwtUserDetailsService userDetailsService;
	
	public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, JwtUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtils = jwtTokenUtils;
		this.userDetailsService = userDetailsService;
	}
	
	@PostMapping(value = RequestConstants.AUTHENTICATE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtils.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping(value = RequestConstants.REGISTER)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
