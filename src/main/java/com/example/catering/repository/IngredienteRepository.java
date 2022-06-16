package com.example.catering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;

public interface IngredienteRepository extends CrudRepository <Ingrediente,Long>{
	
	public List<Ingrediente> findByNome(String nome);
	
	public Ingrediente findByNomeAndPiatto(String nome, Piatto piatto);
	

}