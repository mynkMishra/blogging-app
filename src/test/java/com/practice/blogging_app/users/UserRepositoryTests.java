package com.practice.blogging_app.users;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.practice.blogging_app.users.entities.UserEntity;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
	
	@Autowired
	private IUsersRepository userRepository;
	
	@Test
	@Order(1)
	void can_create_users() {
		var user = UserEntity.builder()
				.usenrame("arnavgupta")
				.email("arnav@bloggingapp.com")
				.password("password")
				.build();
		
		userRepository.save(user);
	}
	
	@Test
	@Order(2)
	void can_find_users() {
		var user = UserEntity.builder()
				.usenrame("arnavgupta")
				.email("arnav@bloggingapp.com")
				.password("password")
				.build();
		
		userRepository.save(user);
		var users = userRepository.findAll();
		assert users.size() == 1;
	}
}
