package com.childassess.repository;

import org.springframework.data.repository.CrudRepository;

import com.childassess.repository.entity.Assessment;


public interface AssessmentRepository extends CrudRepository<Assessment, Integer> {	
	
}
