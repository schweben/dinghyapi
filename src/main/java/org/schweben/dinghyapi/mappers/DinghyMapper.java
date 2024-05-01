package org.schweben.dinghyapi.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.entities.Rig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DinghyMapper {
	Logger LOGGER = LoggerFactory.getLogger(DinghyMapper.class);

	DinghyDTO map(Dinghy entity, @Context String serverUrl);

	List<DinghyDTO> map(List<Dinghy> dinghies, @Context String serverUrl);

	@ValueMapping(source = "UNA", target = "Una")
	@ValueMapping(source = "SLOOP", target = "Sloop")
	@ValueMapping(source = "CUTTER", target = "Cutter")
	@ValueMapping(source = "YAWL", target = "Yawl")
	@ValueMapping(source = "KETCH", target = "Ketch")
	@ValueMapping(source = "SCHOONER", target = "Schooner")
	String map(Rig rig);

	@BeforeMapping
	default void beforeMapping(Dinghy dinghy, @Context String serverUrl) {
		LOGGER.debug("Converting paths to {}", serverUrl);
		if (dinghy.getImagePath() != null) {
			dinghy.setImagePath(serverUrl + dinghy.getImagePath());
			LOGGER.debug("Dinghy image path now {}", dinghy.getImagePath());
		}

		if (dinghy.getLogoPath() != null) {
			dinghy.setLogoPath(serverUrl + dinghy.getLogoPath());
			LOGGER.debug("Dinghy logo path now {}", dinghy.getLogoPath());
		}
	}
}
