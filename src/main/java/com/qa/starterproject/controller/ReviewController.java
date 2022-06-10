package com.qa.starterproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.starterproject.domain.Review;
import com.qa.starterproject.dto.ReviewDto;
import com.qa.starterproject.exception.ReviewException;
import com.qa.starterproject.service.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {
	
	private ReviewService service;

	public ReviewController(ReviewService service) {
		this.service = service;
	}

	// create
	@PostMapping("/create")
	public ResponseEntity<ReviewDto> create(@RequestBody Review review) {
		return new ResponseEntity<ReviewDto>(this.service.create(review), HttpStatus.CREATED);
	}

	// read
	@GetMapping("/readAll")
	public ResponseEntity<List<ReviewDto>> read() {
		return new ResponseEntity<List<ReviewDto>>(this.service.readAll(), HttpStatus.OK);
	}

	// read ID
	@GetMapping("/read/{id}")
	public ResponseEntity<ReviewDto> readId(@PathVariable Long id) throws ReviewException {
		return new ResponseEntity<ReviewDto>(this.service.readId(id), HttpStatus.OK);
	}

	// find review by rating
	@GetMapping("/rating/{num}")
	public ResponseEntity<List<ReviewDto>> findByTitle(@PathVariable int num) {
		return new ResponseEntity<List<ReviewDto>>(this.service.findByRating(num), HttpStatus.OK);
	}
	

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<ReviewDto> update(@PathVariable Long id, @RequestBody Review review) throws ReviewException {
		return new ResponseEntity<ReviewDto>(this.service.update(id, review), HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) throws ReviewException {
		this.service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
