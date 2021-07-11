package com.thubaas.sfgpetclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.thubaas.sfgpetclinic.models.PetType;
import com.thubaas.sfgpetclinic.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
	
	private final PetTypeService petTypeService;
	
	public PetTypeFormatter(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale){
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {

		Collection<PetType> findPetTypes = petTypeService.findAll();
		
		for(PetType petType : findPetTypes) {
			if(petType.getName().equals(text)) {
				return petType;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
