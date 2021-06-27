package com.thubaas.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.models.PetType;
import com.thubaas.sfgpetclinic.models.Vet;
import com.thubaas.sfgpetclinic.services.OwnerService;
import com.thubaas.sfgpetclinic.services.PetTypeService;
import com.thubaas.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {
	
	public final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Gleananne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded owners...");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Sean");
		vet2.setLastName("Angel");
		
		vetService.save(vet2);
		
		System.out.println("Loaded vets...");
		
		
	}

}
