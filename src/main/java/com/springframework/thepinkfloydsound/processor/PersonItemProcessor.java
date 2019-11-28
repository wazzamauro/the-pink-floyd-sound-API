package com.springframework.thepinkfloydsound.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.repository.PersonRepository;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {
	
	@Autowired
	PersonRepository personRepository;

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {
		log.info("Found " + person);
		return person;
	}

}
