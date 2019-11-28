package com.springframework.thepinkfloydsound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springframework.thepinkfloydsound.entity.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long>{

}
