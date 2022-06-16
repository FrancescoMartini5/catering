package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.repository.IngredienteRepository;
import com.example.catering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList <Piatto>();
		
		for(Piatto p : piattoRepository.findAll())
				piatti.add(p);
		return piatti;
		}
	
	@Transactional
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	@Transactional
	public Piatto findByNome(String nome) {
		List <Piatto> piatti = piattoRepository.findByNome(nome);
		return piatti.get(0);
	}
	
	
	@Transactional
	public void savePiatto(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
	@Transactional
	public void deletePiatto(Piatto piatto) {
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		ingredienteRepository.deleteAll(ingredienti);
		
		piattoRepository.delete(piatto);
		
	}
	
	
	@Transactional
	public boolean alreadyExists(Piatto piatto) {
		Piatto p = this.piattoRepository.findByNomeAndBuffet(piatto.getNome(), piatto.getBuffet());
		
		if(p != null)
			return true;
		else
			return false;
	}

	public PiattoRepository getPiattoRepository() {
		return piattoRepository;
	}

	public void setPiattoRepository(PiattoRepository piattoRepository) {
		this.piattoRepository = piattoRepository;
	}
	
}
