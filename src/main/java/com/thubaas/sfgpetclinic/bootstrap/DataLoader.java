package com.thubaas.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.models.Pet;
import com.thubaas.sfgpetclinic.models.PetType;
import com.thubaas.sfgpetclinic.models.Specialty;
import com.thubaas.sfgpetclinic.models.Vet;
import com.thubaas.sfgpetclinic.services.OwnerService;
import com.thubaas.sfgpetclinic.services.PetTypeService;
import com.thubaas.sfgpetclinic.services.SpecialtyService;
import com.thubaas.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {
	
	public final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		
		if(count == 0) {
			loadData();
		}
		
		
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology = specialtyService.save(radiology);
		
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty savedSurgury = specialtyService.save(surgery);
		
		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedDentistry = specialtyService.save(dentistry);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("1231231231");
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Gleananne");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("1231231231");
		
		Pet fionasPet = new Pet();
		fionasPet.setPetType(savedCatPetType);
		fionasPet.setOwner(owner2);
		fionasPet.setBirthDate(LocalDate.now());
		fionasPet.setName("Moscow");
		owner2.getPets().add(fionasPet);
		
		ownerService.save(owner2);
		
		System.out.println("Loaded owners...");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialties().add(savedRadiology);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Sean");
		vet2.setLastName("Angel");
		vet2.getSpecialties().add(savedSurgury);
		
		vetService.save(vet2);
		
		System.out.println("Loaded vets...");
	}

}
