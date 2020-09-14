package com.childassess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.childassess.exception.ChildProfileAlreadyExistsException;
import com.childassess.repository.entity.ChildProfile;
import com.childassess.services.ChildAssessService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/childassess")
public class ChildAssessController {
	public static final String MIME_JSON = "application/json";

	@Autowired
	private ChildAssessService childAssessService;

	@PostMapping(consumes = { MIME_JSON }, produces = { MIME_JSON })
	@ApiOperation(value = "Register New Child", notes = "Used For registerig new child", nickname = "createNewChild",authorizations= {@Authorization("Authorization")})
	@ApiResponses({ @ApiResponse(code = 201, message = "Child Registered"),
			@ApiResponse(code = 409, message = "Child Already registered In Database") })
	public ResponseEntity<?> createNewChild(
			@ApiParam(value = "Child Request", required = true) @RequestBody final ChildProfile child) {
		System.out.println("Child:registerNewChild:" + child);
		try {
			childAssessService.createChildProfile(child);
		} catch (ChildProfileAlreadyExistsException exception) {
			return new ResponseEntity<>("ErrorMessage:Create::" + exception.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(child, HttpStatus.CREATED);
	}
	
	/*

	@PutMapping(path = "/{id}", consumes = { MIME_JSON }, produces = { MIME_JSON })
	@ApiOperation(value = "Update Existing Movie", notes = "Used For Updating Movie Information", nickname = "updateMovieInformation",authorizations= {@Authorization("Authorization")})
	@ApiResponses({ @ApiResponse(code = 200, message = "Movie Information Updated"),
			@ApiResponse(code = 409, message = "Movie Not Exists In Database") })
	public ResponseEntity<?> updateMovieInformation(
			@ApiParam(value = "Movie Id For Update", required = true) @PathVariable final Integer id,
			@ApiParam(value = "Movie Request", required = true) @RequestBody final Movie movie,
			@ApiParam(value = "Logged In User Id", required = true)@RequestParam("userId")String userId) {
		try {
			System.out.println("Movie:updateMovieInformation:" + movie + "[" + id + "]");
			final Movie fechedMovie = movieService.updateMovieInformation(movie,userId);
			return ResponseEntity.ok(fechedMovie);
		} catch (MovieNotFoundException exception) {
			return new ResponseEntity<>("ErrorMessage:Update::" + exception.getMessage() + "\"}", HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Delete Existing Movie", notes = "Used For Deleting Movie Information", nickname = "deleteMovieByMovieId",authorizations= {@Authorization("Authorization")})
	@ApiResponses({ @ApiResponse(code = 200, message = "Movie Information Deleted"),
			@ApiResponse(code = 404, message = "Movie Not Exists In Database") })
	public ResponseEntity<String> deleteMovieByMovieId(
			@ApiParam(value = "Movie Id For Delete", required = true) @PathVariable final Integer id,
			@ApiParam(value = "Logged In User Id", required = true)@RequestParam("userId")String userId) {
		System.out.println("Movie:deleteMovieByMovieId:" + "[" + id + "]");
		try {
			movieService.deleteMovieByMovieId(id,userId);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<>("ErrorMessage:Delete::" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok("Movie Deletion Sucessful.");
	}

	@GetMapping(path = "/{id}", produces = { MIME_JSON })
	@ApiOperation(value = "Get Existing Movie", notes = "Used For Get Movie Information", nickname = "getMovieByMovieId",authorizations= {@Authorization("Authorization")})
	@ApiResponses({ @ApiResponse(code = 200, message = "Movie Information Retrieved"),
			@ApiResponse(code = 404, message = "Movie Not Exists In Database") })
	public ResponseEntity<?> getMovieByMovieId(
			@ApiParam(value = "Movie Id For Retrieval", required = true) @PathVariable final Integer id,
			@ApiParam(value = "Logged In User Id", required = true)@RequestParam("userId")String userId) {
		System.out.println("Movie:getMovieByMovieId:" + "[" + id + "]");
		try {
			return ResponseEntity.ok(movieService.getMovieByMovieId(id,userId));
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<>("ErrorMessage:Get::" + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(produces = { MIME_JSON })
	@ApiOperation(value = "Get All Existing Movie", notes = "Used For Get All Movie Information", nickname = "getAllMovies",authorizations= {@Authorization("Authorization")})
	@ApiResponses({ @ApiResponse(code = 200, message = "All Movie Information Retrieved") })
	public ResponseEntity<List<Movie>> getAllMovies(@ApiParam(value = "Logged In User Id", required = true)@RequestParam("userId")String userId) {
		return ResponseEntity.ok(movieService.getAllMovies(userId));
	}
	*/
}
