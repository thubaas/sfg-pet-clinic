package com.thubaas.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.models.Vet;
import com.thubaas.sfgpetclinic.services.OwnerService;
import com.thubaas.sfgpetclinic.services.VetService;
import com.thubaas.sfgpetclinic.services.map.OwnerServiceMap;
import com.thubaas.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {
	
	public final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader() { 
		super();
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Gleananne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded owners...");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(1L);
		vet2.setFirstName("Sean");
		vet2.setLastName("Angel");
		
		vetService.save(vet2);
		
		System.out.println("Loaded vets...");
		
		
	}

}
