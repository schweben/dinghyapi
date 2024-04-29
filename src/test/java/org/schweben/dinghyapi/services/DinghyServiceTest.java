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

	@BeforeEach
	public void init() {
		dummyDinghies = new ArrayList<>();
		dummyDinghies.add(
				Dinghy.builder().id(1).name("Dummy 1").manufacturer("Dummy A").crew(1).symmetricSpinnaker(false)
						.asymmetricSpinnaker(false).trapeze(0).hulls(1).build());
		dummyDinghies.add(
				Dinghy.builder().id(2).name("Dummy 2").manufacturer("Dummy A").crew(1).symmetricSpinnaker(false)
						.asymmetricSpinnaker(true).trapeze(1).hulls(1).build());
		dummyDinghies.add(
				Dinghy.builder().id(3).name("Dummy 3").manufacturer("Dummy B").crew(2).symmetricSpinnaker(true)
						.asymmetricSpinnaker(false).trapeze(0).hulls(1).build());
		dummyDinghies.add(
				Dinghy.builder().id(4).name("Dummy 4").manufacturer("Dummy B").crew(2).symmetricSpinnaker(false)
						.asymmetricSpinnaker(true).trapeze(1).hulls(1).build());
		dummyDinghies.add(
				Dinghy.builder().id(5).name("Dummy 5").manufacturer("Dummy B").crew(2).symmetricSpinnaker(false)
						.asymmetricSpinnaker(true).trapeze(2).hulls(1).build());

		DinghyMapper mapper = Mappers.getMapper(DinghyMapper.class);
		ReflectionTestUtils.setField(target, "mapper", mapper);
	}

	@Test
	public void givenName_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(DINGHY_CLASS_NAME);

		Assertions.assertEquals(5, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 1", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNoParams_whenGetAllSymmetricDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getAllSymmetricDinghies();

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNoParams_whenGetAllAsymmetricDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getAllAsymmetricDinghies();

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 2", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(2).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenSingleTrapeze_whenGetDinghiesWithTrapeze_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(1);

		Assertions.assertEquals(2, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenDoubleTrapeze_whenGetDinghiesWithTrapeze_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(2);

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(0).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenInvalidTrapezeNum_whenGetDinghiesWithTrapeze_returnEmptyList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze(42);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenManufacturer_whenGetDinghiesFromManufacturer_returnPopulatedList() {
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
	public void givenInvalidManufacturer_whenGetDinghiesFromManufacturer_returnEmptyList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesFromManufacturer("banana");

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenCrewNum_whenGetDinghiesWithCrew_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithCrew(2);

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(2).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenInvalidCrewNum_whenGetDinghiesWithCrew_returnEmptyList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithCrew(42);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenHullNum_whenGetDinghiesWithHulls_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithHulls(1);

		Assertions.assertEquals(5, results.size());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 1", results.get(0).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 2", results.get(1).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 3", results.get(2).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 4", results.get(3).name());
		Assertions.assertEquals(DINGHY_CLASS_NAME + " 5", results.get(4).name());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenInvalidHullNum_whenGetWithHulls_returnEmptyList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithHulls(42);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNoParams_whenGetDinghiesWithTrapeze_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghiesWithTrapeze();

		Assertions.assertEquals(3, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNameOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies("Dummy 1", null, null, null, null, null);

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(1, results.get(0).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenManufacturerOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, "Dummy A", null, null, null, null);

		Assertions.assertEquals(2, results.size());
		Assertions.assertEquals(1, results.get(0).id());
		Assertions.assertEquals(2, results.get(1).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenCrewOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, null, 2, null, null, null);

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(3, results.get(0).id());
		Assertions.assertEquals(4, results.get(1).id());
		Assertions.assertEquals(5, results.get(2).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenSymmetricOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, null, null, true, null, null);

		Assertions.assertEquals(1, results.size());
		Assertions.assertEquals(3, results.get(0).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenAsymmetricOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, null, null, null, true, null);

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(2, results.get(0).id());
		Assertions.assertEquals(4, results.get(1).id());
		Assertions.assertEquals(5, results.get(2).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenTrapezeOnly_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, null, null, null, null, true);

		Assertions.assertEquals(3, results.size());
		Assertions.assertEquals(2, results.get(0).id());
		Assertions.assertEquals(4, results.get(1).id());
		Assertions.assertEquals(5, results.get(2).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	public void givenManufacturerAndTrapeze_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, "Dummy B", null, null, null, true);

		Assertions.assertEquals(2, results.size());
		Assertions.assertEquals(4, results.get(0).id());
		Assertions.assertEquals(5, results.get(1).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNoFilters_whenGetDinghies_returnFullList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(dummyDinghies);

		List<DinghyDTO> results = target.getDinghies(null, null, null, null, null, null);

		Assertions.assertEquals(5, results.size());
		Assertions.assertEquals(1, results.get(0).id());
		Assertions.assertEquals(2, results.get(1).id());
		Assertions.assertEquals(3, results.get(2).id());
		Assertions.assertEquals(4, results.get(3).id());
		Assertions.assertEquals(5, results.get(4).id());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenNameAndDinghyWithNoName_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(
				List.of(Dinghy.builder().id(1).manufacturer("Dummy").crew(1).symmetricSpinnaker(false)
						.asymmetricSpinnaker(false).trapeze(0).hulls(1).build()));

		List<DinghyDTO> results = target.getDinghies("Dummy", null, null, null, null, null);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}

	@Test
	public void givenManufacturerAndDinghyWithNoManufacturer_whenGetDinghies_returnPopulatedList() {
		Mockito.when(mockDinghyRepository.findAll()).thenReturn(
				List.of(Dinghy.builder().id(1).name("Dummy").crew(1).symmetricSpinnaker(false)
						.asymmetricSpinnaker(false).trapeze(0).hulls(1).build()));

		List<DinghyDTO> results = target.getDinghies(null, "Dummy", null, null, null, null);

		Assertions.assertEquals(0, results.size());
		Mockito.verify(mockDinghyRepository).findAll();
	}
}
