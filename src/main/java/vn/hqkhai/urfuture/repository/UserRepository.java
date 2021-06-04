package vn.hqkhai.urfuture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vn.hqkhai.urfuture.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);

}
