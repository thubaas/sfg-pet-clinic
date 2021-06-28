package com.thubaas.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.models.Pet;
import com.thubaas.sfgpetclinic.services.OwnerService;
import com.thubaas.sfgpetclinic.services.PetService;
import com.thubaas.sfgpetclinic.services.PetTypeService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
	
	private final PetTypeService petTypeService;
	private final PetService petService;
	
	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Owner save(Owner owner) {
		if(owner != null) {
			if(owner.getPets() != null) {
				owner.getPets().forEach(pet -> {
					if(pet.getPetType() != null) {
						if(pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					} else {
						throw new RuntimeException("Pet Type is required.");
					}
					
					if(pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(owner);
		}
		return null;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	} 

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void delete(Owner owner) {
		super.delete(owner);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}

}
