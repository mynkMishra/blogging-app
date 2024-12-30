package com.practice.blogging_app.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);

}
