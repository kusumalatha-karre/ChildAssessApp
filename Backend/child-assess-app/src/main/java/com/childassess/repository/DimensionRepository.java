package com.childassess.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.childassess.repository.entity.Dimension;


public interface DimensionRepository extends CrudRepository<Dimension, Integer> {	
	Optional<Dimension> findByDimensionName(String dimensionName);
}
