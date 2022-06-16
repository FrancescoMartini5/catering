package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.repository.BuffetRepository;
import com.example.catering.repository.IngredienteRepository;
import com.example.catering.repository.PiattoRepository;

@Service
public class BuffetService {
	
	public BuffetRepository getBuffetRepository() {
		return buffetRepository;
	}

	public void setBuffetRepository(BuffetRepository buffetRepository) {
		this.buffetRepository = buffetRepository;
	}
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Transactional
	public void saveBuffet(Buffet buffet) {
		buffetRepository.save(buffet);
	}
	
	@Transactional
	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}
	
	@Transactional
	public Buffet findByNome(String nome) {
		List <Buffet> buffet = buffetRepository.findByNome(nome);
		return buffet.get(0);
	}
	
	@Transactional
	public void deleteBuffet(Buffet buffet) {
		List<Piatto> piatti = buffet.getPiatti();
		for(Piatto p : piatti) {
			List<Ingrediente> ingredienti = p.getIngredienti();
		ingredienteRepository.deleteAll(ingredienti);
		}
		piattoRepository.deleteAll(piatti);
		buffetRepository.delete(buffet);
	}
	
	
	public List<Buffet> findAll(){
		List<Buffet> buffet = new ArrayList <Buffet>();
		
		for(Buffet b : buffetRepository.findAll())
				buffet.add(b);
		return buffet;
		}

	
	@Transactional
	public boolean alreadyExists(Buffet buffet) {
		Chef chef = buffet.getChef();
		Buffet b = this.buffetRepository.findByNomeAndChef(buffet.getNome(),chef);
		
		if(b != null)
			return true;
			else
				return false;
		
		
	}

}
