package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public IngredienteRepository getIngredienteRepository() {
		return ingredienteRepository;
	}

	public void setIngredienteRepository(IngredienteRepository ingredienteRepository) {
		this.ingredienteRepository = ingredienteRepository;
	}

	@Transactional
	public void saveIngrediente(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}
	
	@Transactional
	public Ingrediente findByNome(String nome) {
		List <Ingrediente> ingrediente = ingredienteRepository.findByNome(nome);
		return ingrediente.get(0);
	}
	
	@Transactional
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingrediente = new ArrayList <Ingrediente>();
		
		for(Ingrediente i : ingredienteRepository.findAll())
			ingrediente.add(i);
		return ingrediente;
		}

	
	@Transactional
	public boolean alreadyExists(Ingrediente ingrediente) {
		Ingrediente i = ingredienteRepository
				.findByNomeAndPiatto(ingrediente.getNome(),ingrediente.getPiatto());
		
		if (i != null)
			return true;
		else 
			return false;
	}

}
