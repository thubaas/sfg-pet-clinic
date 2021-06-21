package com.thubaas.sfgpetclinic.services.map;

import java.util.Set;

import com.thubaas.sfgpetclinic.models.Vet;
import com.thubaas.sfgpetclinic.services.VetService;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	@Override
	public Vet save(Vet vet) {
		return super.save(vet.getId(), vet);
	}
	
	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}
	
	@Override 
	public Vet findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public void delete(Vet vet) {
		super.delete(vet);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
