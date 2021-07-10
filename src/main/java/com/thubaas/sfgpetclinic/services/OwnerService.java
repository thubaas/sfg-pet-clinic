package com.thubaas.sfgpetclinic.services;

import java.util.List;

import com.thubaas.sfgpetclinic.models.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
	List<Owner> findAllByLastNameLike(String lastName);
	
}
