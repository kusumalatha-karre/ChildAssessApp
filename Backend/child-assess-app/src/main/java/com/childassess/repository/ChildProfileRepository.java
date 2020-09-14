package com.childassess.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.childassess.repository.entity.ChildProfile;


public interface ChildProfileRepository extends CrudRepository<ChildProfile, Integer> {
	Optional<ChildProfile> findByEmailAndPassword(String email, String password);	
	Optional<ChildProfile> findByEmail(String email);
}
