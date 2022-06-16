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

import com.example.catering.model.Buffet;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.service.BuffetService;
import com.example.catering.service.IngredienteService;
import com.example.catering.service.PiattoService;
import com.example.catering.validator.IngredienteValidator;

@Controller
public class IngredienteController {

	@Autowired 
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	public IngredienteValidator ingredienteValidator;


	public IngredienteService getIngredienteService() {
		return ingredienteService;
	}
	
	@PostMapping("/admin/chef/buffet/piatto/ingredienti/{id}")
	public String addIngredienteAtPiatto(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,
			@PathVariable ("id")Long id,
			BindingResult bindingResult, Model model) {
		Piatto p = piattoService.findById(id);
		Buffet b = buffetService.findById(p.getIdBuffet());
		ingrediente.setPiatto(p);
		ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			p.getIngredienti().add(ingrediente);
			piattoService.savePiatto(p);
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("b", b);
			model.addAttribute("p",p);
			return "ingredienteForm.html";
			}
		else {
			model.addAttribute("b", b);
			model.addAttribute("p",p);
			return "ingredienteForm.html"; 
			}
		}
	
	@GetMapping("/admin/chef/buffet/piatto/ingrediente/{id}")
	public String showPiattoForm(@PathVariable ("id")Long id, Model model) {
		Piatto p = piattoService.findById(id);
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("p", p);
		return "piattoForm.html";
	}
	
	@GetMapping("/admin/piatto/ingredienti/{id}")
	public String ingredienti(@PathVariable ("id")Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("piatto", piatto);
		return "admin/ingredienti";}

	
	@GetMapping("/ingredienti")
	public String getAllIngrediente(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti.html";
	}
	
	@GetMapping("/piatto/ingredienti/{id}")
	public String getIngredientiInPiatto(@PathVariable ("id")Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		List<Ingrediente> ingredienti = piatto.getIngredienti();
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("piatto", piatto);
		return "ingredienti";
	}
	

	
	@GetMapping("/ingrediente/{id}")
	public String getIngredienteById(@PathVariable ("id")Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}

	@GetMapping("admin/ingrediente/{id}")
	public String getIngredienteByIdAdmin(@PathVariable ("id")Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "admin/ingrediente.html";
	}
	
	
	public void setIngredienteService(IngredienteService ingredienteService) {
		this.ingredienteService = ingredienteService;
	}

}
