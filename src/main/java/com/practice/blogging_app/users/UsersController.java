package com.practice.blogging_app.users;

import java.net.URI;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.blogging_app.common.dtos.ErrorResponseDTO;
import com.practice.blogging_app.users.UsersService.UserNotFoundException;
import com.practice.blogging_app.users.dtos.CreateUserRequestDTO;
import com.practice.blogging_app.users.dtos.LoginUserRequestDTO;
import com.practice.blogging_app.users.dtos.ProfileResponseDTO;
import com.practice.blogging_app.users.dtos.UserResponseDTO;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	private UsersService usersService;
	private ModelMapper modelMapper;
	
	public UsersController(UsersService userService, ModelMapper modelMapper) {
		this.usersService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO request) {
		var user = usersService.createUser(request);
		URI location = URI.create("/users/" + user.getId());
		var response = modelMapper.map(user, UserResponseDTO.class);
		return ResponseEntity.created(location).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseDTO> login(@RequestBody LoginUserRequestDTO request){
		var user = usersService.loginUser(request);
		var response = modelMapper.map(user, UserResponseDTO.class);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/profiles")
	public ResponseEntity<ArrayList<ProfileResponseDTO>> getProfile(){
		var users = usersService.getAllUsers();
		
		ArrayList<ProfileResponseDTO> profiles = new ArrayList<ProfileResponseDTO>();
		for(UserEntity u: users) {
			profiles.add(modelMapper.map(u, ProfileResponseDTO.class));
		}
		
		return ResponseEntity.ok(profiles);
	}
	
	@GetMapping("/profiles/{username}")
	public ResponseEntity<ProfileResponseDTO> getProfile(
			@PathVariable(name = "username")
			@Parameter(name = "username")
			String username){
		var user = usersService.getUser(username);
		
		var profile = modelMapper.map(user, ProfileResponseDTO.class);
		
		return ResponseEntity.ok(profile);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(Exception ex){
		String message;
		HttpStatus status;
		
		if(ex instanceof UsersService.UserNotFoundException) {
			message = ex.getMessage();
			status = HttpStatus.NOT_FOUND;
		}else {
			message = "Something went wrong";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		ErrorResponseDTO error = ErrorResponseDTO.builder().message(message).build();
		
		return ResponseEntity.status(status).body(error);
	}
}
