package com.robert.properLad.repos;

import org.springframework.data.repository.CrudRepository;

import com.robert.properLad.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
