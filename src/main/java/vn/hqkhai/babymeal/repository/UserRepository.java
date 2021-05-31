package vn.hqkhai.babymeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.babymeal.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);

}
