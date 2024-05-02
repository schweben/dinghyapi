package org.schweben.dinghyapi.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableCaching
@EnableScheduling
@Configuration
public class CacheConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

	@CacheEvict(value = "dinghies", allEntries = true)
	@Scheduled(fixedRateString = "${dinghysearch.cache.ttl}")
	public void emptyDinghiesCache() {
		LOGGER.debug("Emptying dinghies cache");
	}
}
