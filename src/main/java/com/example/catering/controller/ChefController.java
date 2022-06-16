package com.example.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.catering.model.Chef;
import com.example.catering.service.ChefService;
import com.example.catering.validator.ChefValidator;

@Controller
public class ChefController {

	@Autowired 
	private ChefService chefService;
	
	@Autowired
	private ChefValidator chefValidator;

	//get lettura, post scrittura
	//path associato alle classi di dominio

	public ChefService getChefService() {
		return chefService;
	}
	
	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult bindingResult, Model model) {
		
		chefValidator.validate(chef, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			chefService.saveChef(chef);
			model.addAttribute("chef", chef);
			return "admin/home.html";
			}
		else
			return "chefForm.html"; 
		}
	

	
	@GetMapping("/admin/chef")
	public String showChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}

	
	@GetMapping("/chefs")
	public String getAllChef(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}
	
	@GetMapping("/admin/chefs")
	public String getAllChefAdmin(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs.html";
	}

	
	@GetMapping("/chef/{id}")
	public String getBuffetById(@PathVariable ("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";
	}

	@GetMapping("admin/chef/{id}")
	public String getBuffetByIdAdmin(@PathVariable ("id")Long id, Model model) {
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "admin/chef.html";
	}
	
	
	public void setChefService(ChefService chefService) {
		this.chefService = chefService;
	}

}
