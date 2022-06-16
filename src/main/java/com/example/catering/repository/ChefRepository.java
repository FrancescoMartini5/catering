package com.example.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Chef;

public interface ChefRepository extends CrudRepository <Chef,Long>{
	
	public List<Chef> findByCognome(String cognome);
	
	public Chef findByNomeAndCognome(String nome, String Cognome);

}