package com.thubaas.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {
	
//	private static final long OWNER_ID = 1L;

	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController controller;
	
	Set<Owner> owners;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1l).build());
		owners.add(Owner.builder().id(2l).build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
		
	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"))
			.andExpect(model().attributeExists("owner"));
		
	}
	
	@Test
	void processFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
			.thenReturn(Arrays.asList(Owner.builder().id(1L).build(), 
					Owner.builder().id(2L).build()));
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void processFindFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
			.thenReturn(Arrays.asList(Owner.builder().id(1L).build()));
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
	}
	
	@Test
	void displayOwner() throws Exception {
		when(ownerService.findById(anyLong()))
			.thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/123"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1L))));
	}
	
	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processCreationForm() throws Exception {
		when(ownerService.save(any()))
			.thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(any());
	}
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		when(ownerService.findById(anyLong()))
			.thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/1/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processUpdateOwnerForm() throws Exception {
		when(ownerService.save(any()))
			.thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/1/edit"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(any());
	}

}
