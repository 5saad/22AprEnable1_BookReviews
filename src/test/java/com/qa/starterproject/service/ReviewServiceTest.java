package com.qa.starterproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.qa.starterproject.domain.Review;
import com.qa.starterproject.dto.ReviewDto;
import com.qa.starterproject.repo.ReviewRepo;

@SpringBootTest
public class ReviewServiceTest {

	@Autowired
	private ReviewService service;
	@MockBean
	private ReviewRepo repo;

	@Test
	public void readAllTest() {
		List<ReviewDto> expectedReviewsDto = List.of(new ReviewDto(1L, "john", "doe", 5, "cool book"),
				new ReviewDto(2L, "sam", "thomas", 2, "meh book"));
		List<Review> expectedReviews = List.of(new Review(1L, "john", "doe", 5, "cool book"),
				new Review(2L, "sam", "thomas", 2, "meh book"));
		Mockito.when(this.repo.findAll()).thenReturn(expectedReviews);
		assertThat(this.service.readAll()).isEqualTo(expectedReviewsDto);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void readByIdTest() {
		final Optional<Review> review = Optional.of(new Review(1L, "john", "doe", 5, "cool book"));
		final ReviewDto reviewDto = new ReviewDto(1L, "john", "doe", 5, "cool book");
		final long id = 1L;

		Mockito.when(this.repo.findById(id)).thenReturn(review);
		assertThat(this.service.readId(id)).isEqualTo(reviewDto);
	}

	@Test
	public void createTest() {
		final Review created = new Review(1L, "jeff", "smith", 3, "great book");
		final ReviewDto createdDto = new ReviewDto(1L, "jeff", "smith", 3, "great book");

		Mockito.when(this.repo.save(created)).thenReturn(created);
		assertThat(this.service.create(created)).isEqualTo(createdDto);

		Mockito.verify(this.repo, Mockito.times(1)).save(created);
	}
	
	@Test
	public void updateTest() {
		final Optional<Review> book = Optional.of(new Review(1L, "jeff", "smith", 3, "great book"));
		final Review updated = new Review(1L, "changed", "changed", 1, "changed");
		final ReviewDto updatedReviewDto = new ReviewDto(1L, "changed", "changed", 1, "changed");
		final long id = 1L;

		Mockito.when(this.repo.findById(id)).thenReturn(book);
		Mockito.when(this.repo.saveAndFlush(updated)).thenReturn(updated);

		assertThat(this.service.update(id, updated)).isEqualTo(updatedReviewDto);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(updated);

	}

	@Test
	public void deleteTest() {
		final long id = 1L;
		Optional<Review> foundReview = Optional.of(new Review(1L, "jeff", "smith", 3, "great book"));
		Mockito.when(this.repo.findById(id)).thenReturn(foundReview);
		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.delete(id)).isTrue();

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void findByRatingTest() {
		List<Review> expectedReviews = List.of(new Review(2L, "sam", "thomas", 2, "meh book"));
		List<ReviewDto> expectedReviewsDto = List.of(new ReviewDto(2L, "sam", "thomas", 2, "meh book"));
		final int rating = 2;
		
		Mockito.when(this.repo.findByRating(rating)).thenReturn(expectedReviews);
		assertThat(this.service.findByRating(rating)).isEqualTo(expectedReviewsDto);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByRating(rating);
	}

}

