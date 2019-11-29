package com.springframework.thepinkfloydsound.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.repository.MusicianRepository;

public class MusicianWriter implements ItemWriter<Musician> {

	@Autowired
	private MusicianRepository musicianRepository;

	@Override
	public void write(List<? extends Musician> musicians) throws Exception {
		musicianRepository.saveAll(musicians);
	}

}
