package com.example.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);

}