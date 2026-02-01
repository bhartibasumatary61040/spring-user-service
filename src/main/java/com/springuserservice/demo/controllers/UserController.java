package com.springuserservice.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springuserservice.demo.entity.User;
import com.springuserservice.demo.models.UserRequest;
import com.springuserservice.demo.models.UserResponse;
import com.springuserservice.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    
    public ResponseEntity<UserResponse> createUser( @Valid @RequestBody UserRequest userRequest) {
    	
    	User user = userService.saveUser(userRequest);
    	UserResponse userResponse = new UserResponse(user.getId(), user.getName(),user.getEmail());
      
        return ResponseEntity.created(URI.create("/api/v1/users/" + user.getId())).body(userResponse);
       
    }
    
    @GetMapping("/users/{id}")
    
    public ResponseEntity<UserResponse> getUser(@PathVariable long id){
    	
    	UserResponse userResponse = userService.getUserById(id);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    	
    	
    }
    
    @GetMapping("/users")
    
    public ResponseEntity<List<UserResponse>> getAllUsers(){
    	
    	List<UserResponse> userList = userService.getAllUsers();
    	
    	return ResponseEntity.ok(userList);
    }
    
    @DeleteMapping("/users/{id}")
    
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
    	
    	userService.deleteUserById(id);
    	
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	
    }
    
    @PutMapping("/users/{id}")
    
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id,
    		@Valid @RequestBody UserRequest userRequest){
    	
    	UserResponse userResponse = userService.updateUser(id, userRequest);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    	
    	
    }
    	
    	
    
}
