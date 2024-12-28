package com.practice.blogging_app.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.blogging_app.users.entities.UserEntity;

public interface IUsersRepository extends JpaRepository<UserEntity, Long> {

}
