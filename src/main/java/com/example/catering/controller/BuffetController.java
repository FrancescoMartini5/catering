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
import com.example.catering.model.Chef;
import com.example.catering.model.Piatto;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import com.example.catering.validator.BuffetValidator;

@Controller
public class BuffetController {

	@Autowired 
	private BuffetService buffetService;
	
	@Autowired 
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;

	//get lettura, post scrittura
	//path associato alle classi di dominio

	public BuffetService getBuffetService() {
		return buffetService;
	}
	
	@PostMapping("/admin/chef/buffet/{id}")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			@PathVariable ("id")Long id,
			BindingResult bindingResult, Model model) {
		Chef c = this.chefService.findById(id);
		buffet.setChef(c);
		buffetValidator.validate(buffet, bindingResult);
		if(!bindingResult.hasErrors()) {
			c.getBuffet().add(buffet);
			chefService.saveChef(c);
			model.addAttribute("buffet", buffet);
			model.addAttribute("piatto", new Piatto());
			Buffet b = this.buffetService.findByNome(buffet.getNome());			
			model.addAttribute("b", b);
			return "piattoForm.html";
			}
		else {
		model.addAttribute("chef", c);
		return "buffetForm.html";
		}
			 
		}
	
	@GetMapping("/admin/delete/buffet/{id}")
	public String deleteBuffet(@PathVariable ("id")Long id) {
		Buffet buffet = buffetService.findById(id);
		buffetService.deleteBuffet(buffet);
		return "buffetCancellato";
		
	}
	
	
	@GetMapping("/admin/chef/buffet/{id}")
	public String showBuffetForm(@PathVariable ("id")Long id
			,Model model) {
		Chef chef = this.chefService.findById(id);
		model.addAttribute("chef", chef);
		model.addAttribute("buffet",new Buffet());
		return "buffetForm.html";
	}
	
	
	@GetMapping("/admin/buffet/piatto/{id}")
	public String showPiattiInBuffet(@PathVariable ("id")Long id, Model model) {
		Buffet buffet = this.buffetService.findById(id)	;
		model.addAttribute("piatti", buffet.getPiatti());
		model.addAttribute("buffet", buffet);
		return "admin/piatti";
	}
	
	@GetMapping("/buffets")
	public String getAllBuffet(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "buffets.html";
	}
	
	@GetMapping("/admin/buffets")
	public String getAllBuffetAdmin(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "admin/buffets.html";
	}
	
	


	
	@GetMapping("/buffet/{id}")
	public String getBuffetById(@PathVariable ("id")Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "buffet.html";
	}

	@GetMapping("admin/buffet/{id}")
	public String getBuffetByIdAdmin(@PathVariable ("id")Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		model.addAttribute("buffet", buffet);
		return "admin/buffet.html";
	}
	
	
	public void setBuffetService(BuffetService buffetService) {
		this.buffetService = buffetService;
	}

}
