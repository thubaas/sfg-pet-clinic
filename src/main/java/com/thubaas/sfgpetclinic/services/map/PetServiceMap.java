package com.thubaas.sfgpetclinic.services.map;

import java.util.Set;

import com.thubaas.sfgpetclinic.models.Pet;
import com.thubaas.sfgpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

	@Override
	public Pet save(Pet pet) {
		return super.save(pet.getId(), pet);
	}
	
	@Override
	public Set<Pet> findAll(){
		return super.findAll();
	}
	
	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public void delete(Pet pet) {
		super.delete(pet);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
	

}