package com.springframework.thepinkfloydsound.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	public Logger Log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@RequestMapping("/import")
	public void imortPersonService() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Log.info("Importing person from CSV file. . . ");
		personService.importPerson();
		Log.info("Import finished.");

	}
	
	@GetMapping("/get")
	public List<Person> getPeople() {
		Log.info("Retrieve People from DB. . .");
		return personService.getAllPerson();
	}
}
