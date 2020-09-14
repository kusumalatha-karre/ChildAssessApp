package com.childassess.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.childassess.repository.entity.AnswerOption;
import com.childassess.repository.entity.Dimension;


public interface AnswerOptionRepository extends CrudRepository<AnswerOption, Integer> {	
	Optional<AnswerOption> findByOptionId(String optionId);
}
