package org.schweben.dinghyapi.services;

import org.mapstruct.factory.Mappers;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.mappers.DinghyMapper;
import org.schweben.dinghyapi.repositories.DinghyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinghyService {
	@Autowired
	private DinghyRepository dinghyRepository;

	public List<DinghyDTO> getDinghies(String name) {
		DinghyMapper mapper = Mappers.getMapper(DinghyMapper.class);
		return mapper.map(dinghyRepository.findByNameContaining(name));
	}
}
