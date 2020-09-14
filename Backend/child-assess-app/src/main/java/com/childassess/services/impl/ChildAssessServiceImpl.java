package com.childassess.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.childassess.exception.ChildProfileAlreadyExistsException;
import com.childassess.exception.ChildProfileNotFoundException;
import com.childassess.repository.AnswerOptionRepository;
import com.childassess.repository.AssessmentRepository;
import com.childassess.repository.ChildProfileRepository;
import com.childassess.repository.DimensionRepository;
import com.childassess.repository.QuestionRepository;
import com.childassess.repository.entity.AnswerOption;
import com.childassess.repository.entity.Assessment;
import com.childassess.repository.entity.ChildProfile;
import com.childassess.repository.entity.Dimension;
import com.childassess.repository.entity.Question;
import com.childassess.services.ChildAssessService;

@Service
public class ChildAssessServiceImpl implements ChildAssessService {
	private final DimensionRepository dimensionRepository;
	private final ChildProfileRepository childRepository;
	private final QuestionRepository questionRepository;
	private final AssessmentRepository assessmentRepository;
	private final AnswerOptionRepository answerRepository;

	@Autowired
	public ChildAssessServiceImpl(
			final DimensionRepository dimensionRepository, 
			final ChildProfileRepository childRepository,
			final QuestionRepository questionRepository,
			final AssessmentRepository assessmentRepository,
			final AnswerOptionRepository answerRepository) {
		this.dimensionRepository = dimensionRepository;
		this.childRepository = childRepository;
		this.questionRepository = questionRepository;
		this.assessmentRepository = assessmentRepository;
		this.answerRepository = answerRepository;
	}
	
	@Override
	public List<Dimension> getAllDimensions() {
		List<Dimension> dimensions = new ArrayList<Dimension>();
		dimensionRepository.findAll().forEach(dimensions::add);
	    return dimensions;
	}
	
	@Override
	public List<Question> getQuestionsByDimensionName(String dimensionName) {
		List<Question> questions = null;
		Dimension dimension = null;
		final Optional<Dimension> optionalDimension = dimensionRepository.findByDimensionName(dimensionName);
		if (optionalDimension.isPresent()) {
			dimension = optionalDimension.get();
			questions = questionRepository.findByDimension(dimension);
		}
	    return questions;
	}
	
	@Override
	public boolean saveAssessment(String email, String dimensionName, List<Integer> answers) {
		ChildProfile childProfile = null;
		Dimension dimension = null;
		AnswerOption answer = null;
		Assessment assessment = null;
		List<AnswerOption> answerObjs = null;
		Optional<AnswerOption> optionalAnswerOption = null;
		boolean isSaved = false;
		try {
			if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(dimensionName) && (answers!=null && answers.size() > 0)) {
				Optional<ChildProfile> optionalchildProfile = childRepository.findByEmail(email);
				if (optionalchildProfile.isPresent()) {
					childProfile = optionalchildProfile.get();
					Optional<Dimension> optionalDimension = dimensionRepository.findByDimensionName(dimensionName);
					if (optionalDimension.isPresent()) {
						dimension = optionalDimension.get();
						if (childProfile != null && dimension != null) {
							answerObjs = new ArrayList();
							assessment = new Assessment();
							assessment.setChild(childProfile);
							assessment.setDimension(dimension);
							for (Integer optionId : answers) {
								optionalAnswerOption = answerRepository.findById(optionId);
								if (optionalAnswerOption.isPresent()) {
									answer = optionalAnswerOption.get();
									answerObjs.add(answer);								
								}
							}
							assessment.setAnswers(answerObjs);
							assessmentRepository.save(assessment);
							isSaved = true;
						}						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return isSaved;
	}
	
	@Override
	public void createChildProfile(ChildProfile childProfile) throws ChildProfileAlreadyExistsException {
		final Optional<ChildProfile> optionalChild = childRepository.findByEmail(childProfile.getEmail());
		if (optionalChild.isPresent()) {
			throw new ChildProfileAlreadyExistsException("Unable To Save Child, Child Already Exists with this id " + childProfile);
		}
		childRepository.save(childProfile);
	}
	
	@Override
	public ChildProfile getChildProfileByEmailAndPassword(String email, String password) throws ChildProfileNotFoundException {
		final Optional<ChildProfile> childProfile = childRepository.findByEmailAndPassword(email, password);
		return childProfile.orElseThrow(
				() -> new ChildProfileNotFoundException("Unable To Retrieve ChildProfile, child not yet registered :" + email));
	}

	/*
	

	@Override
	public Movie updateMovieInformation(Movie updateMovie,String userId) throws MovieNotFoundException {
		final Optional<Movie> optionalMovie = movieRepository.findByIdAndUserId(updateMovie.getId(),userId);

		final Movie optionalMovieIfExist = optionalMovie.orElseThrow(() -> new MovieNotFoundException(
				"Unable To Update Movie Comment, Movie Not Exists In Your WatchList:" + updateMovie));
		Optional.ofNullable(updateMovie.getComment()).ifPresent(optionalMovieIfExist::setComment);
		
		optionalMovieIfExist.setUserId(userId);//Setting Field Here,Without Using Any Map:Not Required
		movieRepository.save(optionalMovieIfExist);
		return optionalMovieIfExist;
	}

	@Override
	public void deleteMovieByMovieId(int id,String userId) throws MovieNotFoundException {
		final Optional<Movie> movie = movieRepository.findByIdAndUserId(id,userId);
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("Unable To Delete Movie, Movie Id Not Exists In Your WatchList:" + id);
		}
		movie.ifPresent(movieRepository::delete);
	}

	@Override
	public Movie getMovieByMovieId(int id,String userId) throws MovieNotFoundException {
		final Optional<Movie> movie = movieRepository.findByIdAndUserId(id,userId);
		return movie.orElseThrow(
				() -> new MovieNotFoundException("Unable To Retrieve Movie, Movie Id Not Exists In Your WatchList:" + id));
	}

	
	*/
}
