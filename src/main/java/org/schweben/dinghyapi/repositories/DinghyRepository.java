package org.schweben.dinghyapi.repositories;

import org.schweben.dinghyapi.entities.Dinghy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DinghyRepository extends CrudRepository<Dinghy, Integer> {

	@Cacheable("dinghies")
	List<Dinghy> findAll();

	@Cacheable("dinghies")
	List<Dinghy> findByName(String name);

	@Cacheable("dinghies")
	List<Dinghy> findByNameContaining(String name);
}
