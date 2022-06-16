package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}