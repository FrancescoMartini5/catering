package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Chef;
import com.example.catering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	public ChefRepository getChefRepository() {
		return chefRepository;
	}

	public void setChefRepository(ChefRepository chefRepository) {
		this.chefRepository = chefRepository;
	}

	@Transactional
	public void saveChef(Chef chef) {
		chefRepository.save(chef);
	}
	
	@Transactional
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}
	
	@Transactional
	public Chef findByCognome(String cognome) {
		List <Chef> chef = chefRepository.findByCognome(cognome);
		return chef.get(0);
	}
	
	@Transactional
	public List<Chef> findAll(){
		List<Chef> chef = new ArrayList <Chef>();
		
		for(Chef c : chefRepository.findAll())
				chef.add(c);
		return chef;
		}

	
	@Transactional
	public boolean alreadyExists(Chef chef) {
		Chef c = chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome());
		
		if (c != null)
			return true;
		else 
			return false;
	}

}
