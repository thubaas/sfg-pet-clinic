package com.thubaas.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.thubaas.sfgpetclinic.models.Specialty;
import com.thubaas.sfgpetclinic.services.SpecialtiesService;

@Service
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtiesService {
	
	@Override
	public Set<Specialty> findAll(){
		return super.findAll();
	}
	
	@Override
	public Specialty findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Specialty save(Specialty specialty) {
		return super.save(specialty);
	}
	
	@Override
	public void delete(Specialty specialty) {
		super.delete(specialty);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

}
