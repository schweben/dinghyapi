package org.schweben.dinghyapi.repositories;

import org.schweben.dinghyapi.entities.Dinghy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DinghyRepository extends CrudRepository<Dinghy, Integer> {

	List<Dinghy> findByName(String name);
	List<Dinghy> findByNameContaining(String name);
}
