package com.springframework.thepinkfloydsound.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springframework.thepinkfloydsound.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
