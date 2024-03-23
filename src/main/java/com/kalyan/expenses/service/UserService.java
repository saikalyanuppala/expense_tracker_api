package com.kalyan.expenses.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalyan.expenses.dto.UserDTO;
import com.kalyan.expenses.entity.User;
import com.kalyan.expenses.exception.ItemExistsException;
import com.kalyan.expenses.exception.ResourceNotFoundException;
import com.kalyan.expenses.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Transactional
	public User saveUser(UserDTO dto) {
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new ItemExistsException("user with email already exists: " + dto.getEmail());
		}
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(UserDTO dto, Integer id) {
		User existingUser = findUserById(id);
		existingUser.setAge(dto.getAge() != 0 ? dto.getAge() : existingUser.getAge());
		existingUser.setEmail(dto.getEmail() != null ? dto.getEmail() : existingUser.getEmail());
		existingUser.setUserName(dto.getUserName() != null ? dto.getUserName() : existingUser.getUserName());
		existingUser.setPassword(dto.getPassword() != null ? dto.getPassword() : existingUser.getPassword());
		return userRepository.save(existingUser);
	}

	@Transactional
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	public User findUserByEmail(String email) {
		return userRepository.queryByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
	}

	public User findUserById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + id));
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
}
