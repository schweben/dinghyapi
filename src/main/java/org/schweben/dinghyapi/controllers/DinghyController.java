package org.schweben.dinghyapi.controllers;

import org.schweben.dinghyapi.dto.DinghyDTO;
import org.schweben.dinghyapi.services.DinghyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

	@GetMapping("/get/{name}")
	public @ResponseBody ResponseEntity<List<DinghyDTO>> getByName(@PathVariable String name) {
		return new ResponseEntity<>(dinghyService.getDinghies(name), HttpStatus.OK);
	}
}
