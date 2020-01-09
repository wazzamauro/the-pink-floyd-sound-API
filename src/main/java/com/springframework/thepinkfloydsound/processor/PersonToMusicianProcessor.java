package com.springframework.thepinkfloydsound.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.entity.Person;

public class PersonToMusicianProcessor implements ItemProcessor<Person, Musician> {
	
	public static final Logger log = LoggerFactory.getLogger(PersonToMusicianProcessor.class);

	@Override
	public Musician process(Person person) throws Exception {
		Musician musician = new Musician();
		musician.setId(person.getId());
		musician.setFirstName(person.getFirstName());
		musician.setLastName(person.getLastName());
		musician.setAge(person.getAge());
		musician.inferRole();
		log.info("Transform (" + person + ") to (" + musician + ")");
		return musician;
	}

}
