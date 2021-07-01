package com.thubaas.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.thubaas.sfgpetclinic.models.Visit;
import com.thubaas.sfgpetclinic.repositories.VisitRepository;
import com.thubaas.sfgpetclinic.services.VisitService;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
	
	private final VisitRepository visitRepository;
	
	public VisitSDJpaService(VisitRepository visitRepository) {
		super();
		this.visitRepository = visitRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public Visit findById(Long id) {
		return visitRepository.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public void delete(Visit visit) {
		visitRepository.delete(visit);
	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);
	}

}
