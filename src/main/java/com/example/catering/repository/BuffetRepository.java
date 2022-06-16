package com.example.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;

public interface BuffetRepository extends CrudRepository <Buffet,Long>{

	public List<Buffet> findByNome(String nome);
	
	public Buffet findByNomeAndChef(String nome, Chef chef);
}
