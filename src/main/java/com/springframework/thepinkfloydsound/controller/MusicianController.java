package com.springframework.thepinkfloydsound.controller;

import java.util.List;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.service.MusicianService;

@RestController
@RequestMapping("/api/musician")
public class MusicianController {
	@Autowired
	private MusicianService musicianService;

	@RequestMapping("/import")
	public void imortPersonService() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		musicianService.importMusician();
	}

	@GetMapping("/get")
	public List<Musician> getMusicians() {
		return musicianService.getAllMusicians();
	}
}
