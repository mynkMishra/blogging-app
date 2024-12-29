package com.practice.blogging_app.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.practice.blogging_app.users.dtos.CreateUserRequestDTO;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
	
	@Autowired 
	UsersService usersService;
	
	@Test
	void can_create_user() {
		var user = usersService.createUser(new CreateUserRequestDTO("jhon", "password", "john@mail.com"));
		
		Assertions.assertNotNull(user);
		Assertions.assertEquals("jhon", user.getUsername());
	}
}
