package com.childassess.services;

import java.util.List;

import com.childassess.exception.ChildProfileAlreadyExistsException;
import com.childassess.exception.ChildProfileNotFoundException;
import com.childassess.repository.entity.ChildProfile;
import com.childassess.repository.entity.Dimension;
import com.childassess.repository.entity.Question;

public interface ChildAssessService {
	/* Sign Up */
	void createChildProfile(ChildProfile childProfile) throws ChildProfileAlreadyExistsException;
	/* Sign In */
	ChildProfile getChildProfileByEmailAndPassword(String email, String password) throws ChildProfileNotFoundException;

	/* Load Dimensions */
	List<Dimension> getAllDimensions();
	
	List<Question> getQuestionsByDimensionName(String dimensionName);
	
	boolean saveAssessment(String email, String dimensionName, List<Integer> answers);
	
	//getAllQuestions();
	
	
	/*
	 * public Movie updateMovieInformation(final Movie movie,String userId) throws
	 * MovieNotFoundException;
	 * 
	 * public void deleteMovieByMovieId(final int id,String userId) throws
	 * MovieNotFoundException;
	 * 
	 * public Movie getMovieByMovieId(final int id,String userId) throws
	 * MovieNotFoundException;
	 * 
	 * public List<Movie> getAllMovies(String userId);
	 */
}
