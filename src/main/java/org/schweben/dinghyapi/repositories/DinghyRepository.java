package org.schweben.dinghyapi.repositories;

import org.schweben.dinghyapi.entities.Dinghy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DinghyRepository extends JpaRepository<Dinghy, Integer> {

	@Cacheable("dinghies")
	List<Dinghy> findByName(String name);

	@Cacheable("dinghies")
	List<Dinghy> findByNameContaining(String name);
}
