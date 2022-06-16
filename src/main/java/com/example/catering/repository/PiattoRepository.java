package com.example.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Buffet;
import com.example.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository <Piatto,Long>{
	
	public List<Piatto> findByNome(String nome);
	
	public Piatto findByNomeAndBuffet(String nome, Buffet buffet);
}