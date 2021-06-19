package com.thubaas.sfgpetclinic.services;

import com.thubaas.sfgpetclinic.models.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
}
