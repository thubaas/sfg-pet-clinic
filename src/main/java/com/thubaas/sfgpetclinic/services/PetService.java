package com.thubaas.sfgpetclinic.services;

import java.util.Set;

import com.thubaas.sfgpetclinic.models.Pet;

public interface PetService {
	
	Pet findByIdId(Long id);
	
	Pet save(Pet pet);
	
	Set<Pet> findAll();

}
