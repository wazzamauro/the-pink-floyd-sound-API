package com.springframework.thepinkfloydsound.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.repository.PersonRepository;

@Service
public class PersonService {

	public Logger Log = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job importPerson;

	public void importPerson() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobLauncher.run(importPerson, new JobParameters());
	}
	
	public List<Person> getAllPerson() {
		Log.debug(". . .");

		return personRepository.findAll();
	}
}
