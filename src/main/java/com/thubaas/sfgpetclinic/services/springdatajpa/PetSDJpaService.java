package com.thubaas.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.thubaas.sfgpetclinic.models.Pet;
import com.thubaas.sfgpetclinic.repositories.PetRepository;
import com.thubaas.sfgpetclinic.services.PetService;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
	
	private final PetRepository petRepository;
	
	public PetSDJpaService(PetRepository petRepository) {
		super();
		this.petRepository = petRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		petRepository.findAll().forEach(pets::add);
		return pets;
	}

	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public void delete(Pet pet) {
		petRepository.delete(pet);
	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);
	}

}
