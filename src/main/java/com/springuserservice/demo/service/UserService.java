package com.springuserservice.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springuserservice.demo.entity.User;
import com.springuserservice.demo.exception.ResourceNotFoundException;
import com.springuserservice.demo.models.UserRequest;
import com.springuserservice.demo.models.UserResponse;
import com.springuserservice.demo.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	

	public UserService(UserRepository uesrRepository) {
		
		this.userRepository = uesrRepository;
	}



	public User saveUser(UserRequest userRequest) {
		
		User user =  new User(userRequest.name(), userRequest.email());
		
		userRepository.save(user);
		return user;
		
	}



	public UserResponse getUserById(long id) {
		
		User user = userRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("User not found with id " + id));

		UserResponse userResponse = new UserResponse(user.getId(),user.getName(),user.getEmail());
		
		return userResponse;
		
		
	}
	
	public List<UserResponse> getAllUsers(){
	         List<User> allUsers = userRepository.findAll();
		List<UserResponse> userResponsesList = new ArrayList<>();
		
		for(User user : allUsers) {
			
			userResponsesList.add(new UserResponse(user.getId(),user.getName(), user.getEmail()));
		}
		return userResponsesList;
			
		
	}

	public void deleteUserById(long id) {
		userRepository.deleteById(id);
		
		
	}


	public UserResponse updateUser(long id, UserRequest userRequest) {
		 User user = userRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("User not found with id " + id));
		user.setName(userRequest.name());
		user.setEmail(userRequest.email());
		 User savedUser = userRepository.save(user);
		 
		 return new UserResponse(savedUser.getId(),savedUser.getName(), savedUser.getEmail());
	}

}
