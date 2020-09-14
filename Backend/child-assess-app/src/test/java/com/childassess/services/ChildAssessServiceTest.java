package com.childassess.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.childassess.exception.ChildProfileAlreadyExistsException;
import com.childassess.exception.ChildProfileNotFoundException;
import com.childassess.repository.ChildProfileRepository;
import com.childassess.repository.DimensionRepository;
import com.childassess.repository.entity.ChildProfile;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ChildAssessService.class })
public class ChildAssessServiceTest {

	@MockBean
	private ChildProfileRepository childRepository;
	@MockBean
	private DimensionRepository dimensionRepository;
	
	private ChildProfile childProfileFixture;
	
	@Autowired
	private ChildAssessService childAssessService;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private ChildProfile createChildProfile() {
		ChildProfile childProfile = new ChildProfile();
		childProfile.setId(1);
		childProfile.setFirstName("Tony");
		childProfile.setLastName("Roy");
		childProfile.setParentFirstName("Kusumalatha");
		childProfile.setParentLastName("Karre");
		childProfile.setEmail("Kusumalatha.karre@gmail.com");
		childProfile.setPassword("Kusumalatha.karre@gmail.com");
		childProfile.setDateOfBirth("01/01/2015");		
		return childProfile;
	}

	@Test
	public void testCreateNewMovie() throws ChildProfileAlreadyExistsException, ChildProfileNotFoundException {
		this.childProfileFixture = createChildProfile();
		//when(this.childAssessService.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.of(this.movieFixture));
		this.childAssessService.createChildProfile(this.childProfileFixture);
		ChildProfile childProfile = this.childAssessService.getChildProfileByEmailAndPassword("Kusumalatha.karre@gmail.com", "Kusumalatha.karre@gmail.com");
		assertThat(childProfile.getEmail()).isEqualTo("Kusumalatha.karre@gmail.com");
	}

	/*
	@Test
	public void testCreateNewMovie_Rule() throws MovieAlredayExistsException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");
		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.of(this.movieFixture));

		expectedException.expect(MovieAlredayExistsException.class);
		expectedException.expectMessage("Unable To Save Movie, Movie Already Exists In Your WatchList:" + this.movieFixture);

		this.defaultMovieService.createNewMovie(this.movieFixture,this.userId);
	}

	@Test
	public void testCreateNewMovie_NotInDb() throws MovieAlredayExistsException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");
		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.empty());
		when(this.movieRepository.save(this.movieFixture)).thenReturn(this.movieFixture);

		this.defaultMovieService.createNewMovie(this.movieFixture,this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
		verify(this.movieRepository, times(1)).save(this.movieFixture);
	}

	@Test
	public void testUpdateMovieInformation() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");
		Movie movieToSave = createMovie(1, "The Shawshank Redemption", "1994 Updated Comment",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.of(this.movieFixture));
		when(this.movieRepository.save(this.movieFixture)).thenReturn(movieToSave);

		this.defaultMovieService.updateMovieInformation(movieToSave,this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
		verify(this.movieRepository, times(1)).save(this.movieFixture);
		assertThat(movieToSave.getComment()).isEqualTo("1994 Updated Comment");
	}

	@Test
	public void testUpdateMovieInformation_Exception() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.empty());
		expectedException.expect(MovieNotFoundException.class);
		expectedException.expectMessage("Unable To Update Movie Comment, Movie Not Exists In Your WatchList:" + this.movieFixture);

		this.defaultMovieService.updateMovieInformation(this.movieFixture,this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
	}

	@Test
	public void testDeleteMovieByMovieId_MovieNotFoundException() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.empty());
		expectedException.expect(MovieNotFoundException.class);
		expectedException
				.expectMessage("Unable To Delete Movie, Movie Id Not Exists In Your WatchList:" + this.movieFixture.getId());

		this.defaultMovieService.deleteMovieByMovieId(this.movieFixture.getId(),this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
	}

	@Test
	public void testDeleteMovieByMovieId() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.of(this.movieFixture));

		this.defaultMovieService.deleteMovieByMovieId(this.movieFixture.getId(),this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
	}

	@Test
	public void testGetMovieByMovieId_MovieNotFoundException() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.empty());
		expectedException.expect(MovieNotFoundException.class);
		expectedException.expectMessage(
				"Unable To Retrieve Movie, Movie Id Not Exists In Your WatchList:" + this.movieFixture.getId());

		this.defaultMovieService.getMovieByMovieId(this.movieFixture.getId(),this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
	}

	@Test
	public void testGetMovieByMovieId() throws MovieNotFoundException {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByIdAndUserId(this.movieFixture.getId(),this.userId)).thenReturn(Optional.of(this.movieFixture));

		final Movie actualMovie = this.defaultMovieService.getMovieByMovieId(this.movieFixture.getId(),this.userId);

		verify(this.movieRepository, times(1)).findByIdAndUserId(this.movieFixture.getId(),this.userId);
		assertThat(actualMovie).isEqualToComparingFieldByFieldRecursively(this.movieFixture);
	}

	@Test
	public void testGetAllMovies() {
		this.movieFixture = createMovie(1, "The Shawshank Redemption", "1994",
				"https://www.imdb.com/title/tt0111161/?ref_=adv_li_i");

		when(this.movieRepository.findByUserId(this.userId)).thenReturn(Arrays.asList(this.movieFixture));

		List<Movie> movieActual = this.defaultMovieService.getAllMovies(this.userId);

		verify(this.movieRepository, times(1)).findByUserId(this.userId);
		assertThat(movieActual).hasSize(1);
		assertThat(movieActual.get(0)).isEqualToComparingFieldByFieldRecursively(this.movieFixture);
	}
	*/
}