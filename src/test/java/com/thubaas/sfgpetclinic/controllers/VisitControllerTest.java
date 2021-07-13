package com.thubaas.sfgpetclinic.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import com.thubaas.sfgpetclinic.models.Owner;
import com.thubaas.sfgpetclinic.models.Pet;
import com.thubaas.sfgpetclinic.models.PetType;
import com.thubaas.sfgpetclinic.services.PetService;
import com.thubaas.sfgpetclinic.services.VisitService;


@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
	
	private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = 
			"pets/createOrUpdateVisitForm";
	private static final String REDIRECT_OWNERS_1 = 
			"redirect:/owners/{ownerId}";
	
	@Mock
	PetService petService;
	
	@Mock
	VisitService visitService;
	
	@InjectMocks
	VisitController visitController;
	
	private MockMvc mockMvc;
	
	private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
	private final Map<String, String> uriVariables = new HashMap<>();
	private URI visitsUri;
	

	@BeforeEach
	void setUp() throws Exception {
		Long petId = 1L;
		Long ownerId = 1L;
		when(petService.findById(anyLong()))
				.thenReturn(
						Pet.builder()
							.id(petId)
							.birthDate(LocalDate.of(2021, 10, 10))
							.name("Sulivan")
							.visits(new HashSet<>())
							.owner(Owner.builder()
									.id(ownerId)
									.lastName("Doe")
									.firstName("John")
									.build())
							.petType(PetType.builder()
									.name("Dog").build())
							.build()
				);
		
		uriVariables.clear();
		uriVariables.put("ownerId", ownerId.toString());
		uriVariables.put("petId", petId.toString());
		visitsUri = visitsUriTemplate.expand(uriVariables);
		
		mockMvc = MockMvcBuilders
				.standaloneSetup(visitController)
				.build();
		
	}

	@Test
	void testInitNewVisitForm() throws Exception {
		mockMvc.perform(get(visitsUri))
				.andExpect(status().isOk())
				.andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
	}

	@Test
	void testProcessNewVisitForm() throws Exception {
		mockMvc.perform(post(visitsUri)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("date", "2021-10-10")
						.param("description", "Another description"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name(REDIRECT_OWNERS_1))
				.andExpect(model().attributeExists("visit"));
	}

}
