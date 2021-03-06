package com.springframework.thepinkfloydsound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springframework.thepinkfloydsound.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
