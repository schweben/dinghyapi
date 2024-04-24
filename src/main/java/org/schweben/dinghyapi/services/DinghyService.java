package org.schweben.dinghyapi.services;

import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.mappers.DinghyMapper;
import org.schweben.dinghyapi.repositories.DinghyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinghyService {
	@Autowired
	private DinghyRepository dinghyRepository;

	@Autowired
	private DinghyMapper mapper;

	public List<DinghyDTO> getAllDinghies() {
		return mapper.map(dinghyRepository.findAll());
	}

	public List<DinghyDTO> getDinghies(String name) {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(
				allDinghies.stream().filter(dinghy -> dinghy.getName().toLowerCase().contains(name.toLowerCase()))
						.toList());
	}

	public List<DinghyDTO> getAllSymmetricDinghies() {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(allDinghies.stream().filter(Dinghy::isSymmetricSpinnaker).toList());
	}

	public List<DinghyDTO> getAllAsymmetricDinghies() {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(allDinghies.stream().filter(Dinghy::isAsymmetricSpinnaker).toList());
	}

	public List<DinghyDTO> getDinghiesWithTrapeze(int trapezes) {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(allDinghies.stream().filter(dinghy -> dinghy.getTrapeze() == trapezes).toList());
	}

	public List<DinghyDTO> getDinghiesFromManufacturer(String manufacturer) {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(allDinghies.stream()
				.filter(dinghy -> dinghy.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
				.toList());
	}

	public List<DinghyDTO> getDinghiesWithCrew(int crew) {
		List<Dinghy> allDinghies = dinghyRepository.findAll();
		return mapper.map(allDinghies.stream().filter(dinghy -> dinghy.getCrew() == crew).toList());
	}
}
