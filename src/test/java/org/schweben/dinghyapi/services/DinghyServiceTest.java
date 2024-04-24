package org.schweben.dinghyapi.services;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.repositories.DinghyRepository;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DinghyServiceTest {

	@InjectMocks
	private DinghyService target;

	@Mock
	private DinghyRepository mockDinghyRepository;

	@Mock
	private Dinghy mockDinghy;

	@Test
	public void testGetDinghies_noneFound() {
		String name = "banana";
		Mockito.when(mockDinghyRepository.findByNameContaining(name)).thenReturn(Collections.emptyList());

		List<DinghyDTO> results = target.getDinghies(name);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findByNameContaining(name);
	}

	@Test
	public void testGetDinghies_oneFound() {
		String name = "banana";
		Mockito.when(mockDinghyRepository.findByNameContaining(name)).thenReturn(List.of(mockDinghy));
		Mockito.when(mockDinghy.getName()).thenReturn(name);

		List<DinghyDTO> results = target.getDinghies(name);

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals("banana", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findByNameContaining(name);
	}
}
