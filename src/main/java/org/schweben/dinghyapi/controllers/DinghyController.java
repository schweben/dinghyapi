package org.schweben.dinghyapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.services.DinghyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Operation(summary = "Find a dinghy using search options")
	@GetMapping("")
	public @ResponseBody List<DinghyDTO> getWithQueryFilters(@RequestParam(required = false) String name,
			@RequestParam(required = false) String manufacturer, @RequestParam(required = false) Integer crew,
			@RequestParam(required = false) Boolean symmetric, @RequestParam(required = false) Boolean asymmetric,
			@RequestParam(required = false) Boolean trapeze) {

		return dinghyService.getDinghies(name, manufacturer, crew, symmetric, asymmetric, trapeze);
	}
}
