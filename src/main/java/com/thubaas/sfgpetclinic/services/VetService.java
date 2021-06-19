package com.thubaas.sfgpetclinic.services;

import java.util.Set;

import com.thubaas.sfgpetclinic.models.Vet;

public interface VetService {
	
	Vet findByIdId(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();

}
