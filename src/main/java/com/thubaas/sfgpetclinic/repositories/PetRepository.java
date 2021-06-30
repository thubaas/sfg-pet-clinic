package com.thubaas.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.thubaas.sfgpetclinic.models.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
