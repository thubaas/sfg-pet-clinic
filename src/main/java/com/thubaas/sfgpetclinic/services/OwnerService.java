package com.thubaas.sfgpetclinic.services;

import java.util.Set;

import com.thubaas.sfgpetclinic.models.Owner;

public interface OwnerService {
	
	Owner findByLastName(String lastName);
	
	Owner findByIdId(Long id);
	
	Owner save(Owner owner);
	
	Set<Owner> findAll();

}
