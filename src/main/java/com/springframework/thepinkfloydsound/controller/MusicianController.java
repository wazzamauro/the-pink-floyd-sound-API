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

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.service.MusicianService;

@RestController
@RequestMapping("/api/musician")
@CrossOrigin(origins = "http://localhost:4200")
public class MusicianController {

	public Logger Log = LoggerFactory.getLogger(MusicianController.class);

	@Autowired
	private MusicianService musicianService;
	/*
	 * @RequestMapping("/import") public void imortPersonService() throws
	 * JobExecutionAlreadyRunningException, JobRestartException,
	 * JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	 * Log.info("Importing musician from person file. . . ");
	 * musicianService.importMusician(); Log.info("Import finished.");
	 * 
	 * }
	 */

	@GetMapping("/import")
	@ResponseStatus(HttpStatus.OK)
	public HttpStatus importMusicianService() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Log.info("Importing musician from person file. . . ");
		musicianService.importMusician();
		Log.info("Import finished.");
		return HttpStatus.OK;
	}

	@GetMapping("/get")
	public List<Musician> getMusicians() {
		Log.info("Retrieve Musician from DB. . .");
		return musicianService.getAllMusicians();
	}
}
