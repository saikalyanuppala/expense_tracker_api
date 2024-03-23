package com.kalyan.expenses.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.kalyan.expenses.entity.User;

public interface UserRepository extends ListCrudRepository<User, Integer> {

	List<User> findByUserName(String name);
	
	Boolean existsByEmail(String email);

	Optional<User> queryByEmail(String email);
}
