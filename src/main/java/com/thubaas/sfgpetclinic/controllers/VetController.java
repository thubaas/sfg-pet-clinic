package com.thubaas.sfgpetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thubaas.sfgpetclinic.models.Vet;
import com.thubaas.sfgpetclinic.services.VetService;

@Controller
public class VetController {
	
	private final VetService vetService;
	
	public VetController(VetService vetService) {
		super();
		this.vetService = vetService;
	}

	@RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
	public String listVests(Model model) {
		model.addAttribute("vets", vetService.findAll());
		return "vets/index";
	}
	
	@GetMapping("/api/vets")
	public @ResponseBody Set<Vet> getVetsJson() {
		
		return vetService.findAll();
		
	}

}
