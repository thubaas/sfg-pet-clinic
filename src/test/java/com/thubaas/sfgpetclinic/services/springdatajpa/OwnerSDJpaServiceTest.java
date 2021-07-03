package com.thubaas.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.repositories.OwnerRepository;
import com.thubaas.sfgpetclinic.repositories.PetRepository;
import com.thubaas.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {
	
	private static final String LAST_NAME = "Smith";

	@Mock
	OwnerRepository ownerRepository;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	
	Owner returnOwner;
	
	@BeforeEach
	void setUp() {
		
		returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
		
	}
	
	@Test
	void findByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, smith.getLastName());
		verify(ownerRepository).findByLastName(any());
	}
	
	@Test
	void findAll() {
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
		Set<Owner> owners = service.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
		
	}
	
	@Test
	void findById() {
		when(ownerRepository.findById(anyLong()))
			.thenReturn(Optional.of(returnOwner));
		Owner owner = service.findById(1L);
		assertNotNull(owner);
	}
	
	@Test
	void save() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}
	
	@Test
	void delete() {
		service.delete(returnOwner);
		verify(ownerRepository).delete(any());
	}
	
	@Test
	void deleteById() {
		service.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());
	}

}
