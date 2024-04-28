package org.schweben.dinghyapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.services.DinghyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/dinghies")
public class DinghyController {

	@Autowired
	private DinghyService dinghyService;

	@Operation(summary = "Find a dinghy with search options")
	@GetMapping("")
	public @ResponseBody List<DinghyDTO> getWithQueryFilters(@RequestParam(required = false) String name, @RequestParam(required = false) String manufacturer,
		@RequestParam(required = false, defaultValue = "0") Integer crew, @RequestParam(required = false, defaultValue = "false") Boolean symmetric,
		 @RequestParam(required = false, defaultValue = "false") boolean asymmetric, @RequestParam(required = false, defaultValue = "false") boolean trapeze) {

		return dinghyService.getDinghies(name, manufacturer, crew, symmetric, asymmetric, trapeze);
	}

	@Operation(summary = "Find a dinghy class using its name")
	@Parameter(name = "name", description = "Name (or partial name) of the dinghy class")
	@GetMapping("/name/{name}")
	public @ResponseBody List<DinghyDTO> getByName(@PathVariable String name) {
		return dinghyService.getDinghies(name);
	}

	@Operation(summary = "Find all dinghy classes with the given number of crew")
	@Parameter(name = "crew", description = "Number of crew")
	@GetMapping("/crew/{crew}")
	public @ResponseBody List<DinghyDTO> getWithCrewNum(@PathVariable int crew) {
		return dinghyService.getDinghiesWithCrew(crew);
	}

	@Operation(summary = "Find all dinghy classes which have a trapeze")
	@GetMapping("/trapeze")
	public @ResponseBody List<DinghyDTO> getWithTrapeze() {
		return dinghyService.getDinghiesWithTrapeze();
	}
}
