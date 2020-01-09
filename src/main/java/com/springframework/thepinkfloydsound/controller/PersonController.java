package com.springframework.thepinkfloydsound.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.service.PersonService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

	public Logger Log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@GetMapping("/import")
	@ResponseStatus(HttpStatus.OK)
	public HttpStatus importPersonService() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Log.info("Importing person from CSV file. . . ");
		personService.importPerson();
		Log.info("Import finished.");
		return HttpStatus.OK;
	}

	@GetMapping("/get")
	public List<Person> getPeople() {
		Log.info("Retrieve People from DB. . .");
		return personService.getAllPerson();
	}
}
