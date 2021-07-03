package com.thubaas.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thubaas.sfgpetclinic.models.Owner;

class OwnerMapServiceTest {
	
	static final Long OWNER_ID = 1L;
	static final String lastName = "Sibanda";
	OwnerMapService ownerMapService;
	
	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(
				new PetTypeMapService(), new PetMapService());
		ownerMapService
		.save(Owner.builder().id(OWNER_ID).lastName(lastName).build());
	}

	@Test
	void testExistingId() {
		long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id, savedOwner.getId());
	}
	
	@Test
	void saveNoId() {
		Owner savedOwner = ownerMapService.save(Owner.builder().build());
		
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testFindAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(1, ownerSet.size());
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(OWNER_ID);
		assertEquals(OWNER_ID, owner.getId());
	}

	@Test
	void testDeleteOwner() {
		ownerMapService.delete(ownerMapService.findById(OWNER_ID));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testDeleteByIdLong() {
		ownerMapService.deleteById(OWNER_ID);
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void testFindByLastName() {
		
		Owner sibanda = ownerMapService.findByLastName(lastName);
		assertNotNull(sibanda);
		assertEquals(OWNER_ID, sibanda.getId());
		
	}
	
	@Test
	void testFindByLastNameNotExist() {
		Owner lisbert = ownerMapService.findByLastName("Lisbert");
		assertNull(lisbert);
	}

}
