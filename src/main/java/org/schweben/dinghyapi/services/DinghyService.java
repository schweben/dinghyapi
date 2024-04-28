package org.schweben.dinghyapi.services;

import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.mappers.DinghyMapper;
import org.schweben.dinghyapi.repositories.DinghyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinghyService {
	@Autowired
	private DinghyRepository dinghyRepository;

	@Autowired
	private DinghyMapper mapper;

	public List<DinghyDTO> getDinghies(String name, String manufacturer, int crew, boolean symmetric, boolean asymmetric, boolean trapeze) {
		return mapper.map(getAllDinghies().stream()
				.filter(dinghy -> dinghy.getName() == null || dinghy.getName().toLowerCase().contains(name.toLowerCase()))
				.filter(dinghy -> dinghy.getManufacturer() == null || dinghy.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
				.filter(dinghy -> dinghy.getCrew() == 0 || dinghy.getCrew() == crew)
				.filter(dinghy -> dinghy.isSymmetricSpinnaker() == symmetric)
				.filter(dinghy -> dinghy.isAsymmetricSpinnaker() == asymmetric)
				.filter(dinghy -> dinghy.getTrapeze() > 0)
				.toList());
	}

	public List<DinghyDTO> getDinghies(String name) {
		return mapper.map(
				getAllDinghies().stream().filter(dinghy -> dinghy.getName().toLowerCase().contains(name.toLowerCase()))
						.toList());
	}

	public List<DinghyDTO> getAllSymmetricDinghies() {
		return mapper.map(getAllDinghies().stream().filter(Dinghy::isSymmetricSpinnaker).toList());
	}

	public List<DinghyDTO> getAllAsymmetricDinghies() {
		return mapper.map(getAllDinghies().stream().filter(Dinghy::isAsymmetricSpinnaker).toList());
	}

	public List<DinghyDTO> getDinghiesWithTrapeze() {
		return mapper.map(getAllDinghies().stream().filter(dinghy -> dinghy.getTrapeze() > 0).toList());
	}

	public List<DinghyDTO> getDinghiesWithTrapeze(int trapezes) {
		return mapper.map(getAllDinghies().stream().filter(dinghy -> dinghy.getTrapeze() == trapezes).toList());
	}

	public List<DinghyDTO> getDinghiesFromManufacturer(String manufacturer) {
		return mapper.map(getAllDinghies().stream()
				.filter(dinghy -> dinghy.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase()))
				.toList());
	}

	public List<DinghyDTO> getDinghiesWithCrew(int numCrew) {
		return mapper.map(getAllDinghies().stream().filter(dinghy -> dinghy.getCrew() == numCrew).toList());
	}

	public List<DinghyDTO> getDinghiesWithHulls(int numHulls) {
		return mapper.map(getAllDinghies().stream().filter(dinghy -> dinghy.getHulls() == numHulls).toList());
	}

	@Cacheable("dinghies")
	private List<Dinghy> getAllDinghies() {
		return dinghyRepository.findAll();
	}
}
