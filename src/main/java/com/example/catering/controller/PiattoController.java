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
import com.example.catering.service.PiattoService;
import com.example.catering.validator.PiattoValidator;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoValidator piattoValidator;

	@GetMapping("/piatti")
	public String getPiatti(Model model) {
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		return "piatti.html";
	}



	@PostMapping("/admin/chef/buffet/piatto/{id}")
	public String addPiattoAtBuffet(@Valid @ModelAttribute("piatto") Piatto piatto,
			@PathVariable ("id")Long id,
			BindingResult bindingResult, Model model) {
		Buffet b = this.buffetService.findById(id);
		piatto.setBuffet(b);
		piattoValidator.validate(piatto, bindingResult);
		if(!bindingResult.hasErrors()) {
			b.getPiatti().add(piatto);
			buffetService.saveBuffet(b);
			model.addAttribute("piatto", piatto);
			model.addAttribute("b", b);
			model.addAttribute("ingrediente", new Ingrediente());
			Piatto p = this.piattoService.findByNome(piatto.getNome());
			model.addAttribute("p", p);
			return "ingredienteForm.html"; // piatti da inserire nel buffet
		}
		else {
			model.addAttribute("b", b);
			return "piattoForm.html"; 
			}	} 
	
	@GetMapping("/admin/delete/buffet/piatto/{id}")
	public String deletePiatto(@PathVariable ("id")Long id,Model model) {
		Piatto piatto = piattoService.findById(id);
		Buffet buffet = piatto.getBuffet(); 
		buffet.getPiatti().remove(piatto);
		piattoService.deletePiatto(piatto);
		return "admin/home.html";
		
	}
	
	@GetMapping("piatto/{id}")
	public String getPiattoByIdUser(@PathVariable ("id")Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto.html";
	}
	

	@GetMapping("/admin/chef/buffet/piatto/{id}")
	public String showPiattoForm(@PathVariable ("id")Long id, Model model) {
		Buffet b = buffetService.findById(id);
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("b", b);
		return "piattoForm.html";
	}

	@GetMapping("admin/piatto/{id}")
	public String getPiattoById(@PathVariable ("id")Long id, Model model) {
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("ingredienti", piatto.getIngredienti());
		model.addAttribute("piatto", piatto);
		return "admin/piatto.html";
	}
	
	@GetMapping("/buffet/piatto/{id}")
	public String getPiattiInBuffet(@PathVariable ("id")Long id, Model model)
	{
		Buffet buffet = this.buffetService.findById(id)	;
		model.addAttribute("piatti", buffet.getPiatti());
		model.addAttribute("buffet", buffet);
		return "piatti";
	}
	

	public PiattoService getPiattoService() {
		return piattoService;
	}

	public void setPiattoService(PiattoService piattoService) {
		this.piattoService = piattoService;
	}

}
