package org.schweben.dinghyapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.entities.Rig;

import java.util.List;

@Mapper
public interface DinghyMapper {
	DinghyDTO map(Dinghy entity);

	List<DinghyDTO> map(List<Dinghy> dinghies);

	@ValueMapping(source = "UNA", target = "Una")
	@ValueMapping(source = "SLOOP", target = "Sloop")
	@ValueMapping(source = "CUTTER", target = "Cutter")
	@ValueMapping(source = "YAWL", target = "Yawl")
	@ValueMapping(source = "KETCH", target = "Ketch")
	@ValueMapping(source = "SCHOONER", target = "Schooner")
	String map(Rig rig);
}
