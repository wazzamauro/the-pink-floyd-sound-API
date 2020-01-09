package com.springframework.thepinkfloydsound.service;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.repository.MusicianRepository;

@Service
public class MusicianService {
	
	@Autowired
	private MusicianRepository musicianRepository;
	
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job transformPeopleInMusician;

	public void importMusician() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobLauncher.run(transformPeopleInMusician, new JobParameters());
	}
	
	public List<Musician> getAllMusicians() {
		return musicianRepository.findAll();
	}
}
