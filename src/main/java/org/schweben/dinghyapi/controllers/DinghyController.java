package org.schweben.dinghyapi.controllers;

import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.services.DinghyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/dinghies")
public class DinghyController {

	@Autowired
	private DinghyService dinghyService;

	@GetMapping("/name/{name}")
	public @ResponseBody List<DinghyDTO> getByName(@PathVariable String name) {
		return dinghyService.getDinghies(name);
	}

	@GetMapping("/crew/{crew}")
	public @ResponseBody List<DinghyDTO> getWithCrewNum(@PathVariable int crew) {
		return dinghyService.getDinghiesWithCrew(crew);
	}

	@GetMapping("/trapeze")
	public @ResponseBody List<DinghyDTO> getWithTrapeze() {
		return dinghyService.getDinghiesWithTrapeze();
	}
}
