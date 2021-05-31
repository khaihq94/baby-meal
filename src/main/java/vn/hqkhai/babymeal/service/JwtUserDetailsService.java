package vn.hqkhai.babymeal.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hqkhai.babymeal.entity.User;
import vn.hqkhai.babymeal.model.UserDTO;
import vn.hqkhai.babymeal.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
	public User save(UserDTO userDTO) {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(userDTO.getUsername());
		user.setPassword(this.bcryptEncoder.encode(userDTO.getPassword()));
		return userRepository.save(user);
	}

}
