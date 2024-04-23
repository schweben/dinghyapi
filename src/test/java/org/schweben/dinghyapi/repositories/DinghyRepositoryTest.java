package org.schweben.dinghyapi.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.schweben.dinghyapi.entities.Dinghy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class DinghyRepositoryTest {

	@Autowired
	private DinghyRepository dinghyRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testFindByName() {
		List<Dinghy> results = dinghyRepository.findByName("RS Tera Pro");
		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals("RS Tera Pro", results.get(0).getName());
	}

	@Test
	public void testFindByNameContaining() {
		List<Dinghy> results = dinghyRepository.findByNameContaining("Tera");
		Assertions.assertEquals(2, results.size());
	}
}
