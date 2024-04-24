package org.schweben.dinghyapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;

import java.util.List;

@Mapper
public interface DinghyMapper {
	DinghyDTO map(Dinghy entity);
	List<DinghyDTO> map(List<Dinghy> dinghies);
}
