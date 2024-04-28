package org.schweben.dinghyapi.conf;

import org.mapstruct.factory.Mappers;
import org.schweben.dinghyapi.mappers.DinghyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public DinghyMapper mapper() {
		return Mappers.getMapper(DinghyMapper.class);
	}
}
