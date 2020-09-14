package com.childassess.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.childassess.repository.entity.Dimension;
import com.childassess.repository.entity.Question;


public interface QuestionRepository extends CrudRepository<Question, Integer> {	
	List<Question> findByDimension(Dimension dimension);
}
