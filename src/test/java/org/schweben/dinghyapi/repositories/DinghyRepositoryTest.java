package org.schweben.dinghyapi.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.schweben.dinghyapi.entities.Dinghy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class DinghyRepositoryTest {

	@Autowired
	private DinghyRepository dinghyRepository;

	@Test
	public void givenName_whenFindByName_returnPopulatedList() {
		List<Dinghy> results = dinghyRepository.findByName("RS Tera Pro");
		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals("RS Tera Pro", results.get(0).getName());
	}

	@Test
	public void givenName_whenFindByNameContaining_returnPopulatedList() {
		List<Dinghy> results = dinghyRepository.findByNameContaining("Tera");
		Assertions.assertEquals(2, results.size());
	}

	@Test
	public void givenNoParams_whenFindAll_returnPopulatedList() {
		List<Dinghy> results = dinghyRepository.findAll();
		Assertions.assertFalse(results.isEmpty());
	}
}
