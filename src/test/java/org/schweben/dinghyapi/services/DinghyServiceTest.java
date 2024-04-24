package org.schweben.dinghyapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.entities.Dinghy;
import org.schweben.dinghyapi.mappers.DinghyMapper;
import org.schweben.dinghyapi.repositories.DinghyRepository;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DinghyServiceTest {
	private static final String DINGHY_CLASS_NAME = "Dummy";
	private static final String MANUFACTURER = "Dummy";

	List<Dinghy> dummyDinghies;

	@InjectMocks
	private DinghyService target;

	@Mock
	private DinghyRepository mockDinghyRepository;

	private DinghyMapper mapper;

	@Mock
	private Dinghy mockDinghy;

	@Mock
	private DinghyDTO mockDto;

	@BeforeEach
	public void init() {
		dummyDinghies = new ArrayList<>();
		dummyDinghies.add(Dinghy.builder().name("Dummy 1").manufacturer("Dummy A").crew(1).symmetricSpinnaker(false)
				.asymmetricSpinnaker(false).trapeze(0).build());
		dummyDinghies.add(Dinghy.builder().name("Dummy 2").manufacturer("Dummy A").crew(1).symmetricSpinnaker(false)
				.asymmetricSpinnaker(true).trapeze(1).build());
		dummyDinghies.add(Dinghy.builder().name("Dummy 3").manufacturer("Dummy B").crew(2).symmetricSpinnaker(true)
				.asymmetricSpinnaker(false).trapeze(0).build());
		dummyDinghies.add(Dinghy.builder().name("Dummy 4").manufacturer("Dummy B").crew(2).symmetricSpinnaker(false)
				.asymmetricSpinnaker(true).trapeze(1).build());
		dummyDinghies.add(Dinghy.builder().name("Dummy 5").manufacturer("Dummy B").crew(2).symmetricSpinnaker(false)
				.asymmetricSpinnaker(true).trapeze(2).build());

		mapper = Mappers.getMapper(DinghyMapper.class);
		ReflectionTestUtils.setField(target, "mapper", mapper);
	}

	@Test
	public void testGetDinghies() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(DINGHY_CLASS_NAME);

		Assertions.assertEquals(5, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 1", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetAllDinghies() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getAllDinghies();

		Assertions.assertEquals(5, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetAllSymmetricDinghies() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getAllSymmetricDinghies();

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetAllAsymmetricDinghies() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getAllAsymmetricDinghies();

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 2", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(2).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesWithTrapeze_noTrapeze() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(1);

		Assertions.assertEquals(2, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesWithTrapeze_twinTrapeze() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(2);

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesWithTrapeze_noneFound() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(42);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesFromManufacturer() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesFromManufacturer(MANUFACTURER);

		Assertions.assertEquals(5, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 1", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 2", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(2).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(3).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(4).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesFromManufacturer_noneFound() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesFromManufacturer("banana");

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void testGetDinghiesWithCrew() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithCrew(2);

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(2).name());
		Mockito.verify(mockDinghyRepository).findAll();

	}

	@Test
	public void testGetDinghiesWithCrew_noneFound() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithCrew(42);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();

	}
}
