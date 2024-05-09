package org.schweben.dinghyapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.schweben.dinghyapi.services.DinghyService;

@ExtendWith(MockitoExtension.class)
public class DinghyControllerTest {

	@InjectMocks
	private DinghyController target;

	@Mock
	private DinghyService mockDinghyService;

	@Test
	public void givenSearchTerms_whenGetWithQueryFilters_callService() {
		String name = "banana";
		String manufacturer = "fred";
		int crew = 2;
		boolean symmetric = false;
		boolean asymmetric = true;
		boolean trapeze = true;

		target.getWithQueryFilters(name, manufacturer, crew, symmetric, asymmetric, trapeze);

		Mockito.verify(mockDinghyService).getDinghies(name, manufacturer, crew, symmetric, asymmetric, trapeze);
	}
}
