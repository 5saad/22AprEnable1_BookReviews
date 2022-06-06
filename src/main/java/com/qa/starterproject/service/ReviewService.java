package com.qa.starterproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.starterproject.domain.Review;
import com.qa.starterproject.dto.ReviewDto;
import com.qa.starterproject.exception.ReviewException;
import com.qa.starterproject.repo.ReviewRepo;

@Service
public class ReviewService {

	private ModelMapper mapper;

	private ReviewRepo repo;

	public ReviewService(ReviewRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	private ReviewDto mapToDto(Review car) {
		return this.mapper.map(car, ReviewDto.class);
	}

	// create review
	public ReviewDto create(Review review) {
		return this.mapToDto(this.repo.save(review));
	}

	// read review
	public List<ReviewDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// read review by id
	public ReviewDto readId(Long id) throws ReviewException {
		return this.mapToDto(this.repo.findById(id).orElseThrow(ReviewException::new));
	}

	// update review
	public ReviewDto update(Long id, Review review) throws ReviewException {
		Review exists = this.repo.findById(id).orElseThrow(ReviewException::new);
		exists.setFirstName(review.getFirstName());
		exists.setSurname(review.getSurname());
		exists.setRating(review.getRating());
		exists.setReview(review.getReview());
		return this.mapToDto(this.repo.saveAndFlush(exists));
	}

	// delete review
	public boolean delete(Long id) throws ReviewException {
		this.repo.findById(id).orElseThrow(ReviewException::new);
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	// find review by title
	public List<ReviewDto> findByTitle(int num) {
		return this.repo.findByTitle(num).stream().map(this::mapToDto).collect(Collectors.toList());
	}

}
