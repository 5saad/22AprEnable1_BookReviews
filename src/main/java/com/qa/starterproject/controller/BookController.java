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

import com.qa.starterproject.domain.Book;
import com.qa.starterproject.dto.BookDto;
import com.qa.starterproject.service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
	
	private BookService service;

	public BookController(BookService service) {
		this.service = service;
	}

	// create
	@PostMapping("/create")
	public ResponseEntity<BookDto> create(@RequestBody Book book) {
		return new ResponseEntity<BookDto>(this.service.create(book), HttpStatus.CREATED);
	}

	// read
	@GetMapping("/readAll")
	public ResponseEntity<List<BookDto>> read() {
		return new ResponseEntity<List<BookDto>>(this.service.readAll(), HttpStatus.OK);
	}

	// read ID
	@GetMapping("/read/{id}")
	public ResponseEntity<BookDto> readId(@PathVariable Long id) throws Exception {
		return new ResponseEntity<BookDto>(this.service.readId(id), HttpStatus.OK);
	}

	// find book by author
	@GetMapping("/author/{str}")
	public ResponseEntity<List<BookDto>> findByTitle(@PathVariable String str) {
		return new ResponseEntity<List<BookDto>>(this.service.findByAuthor(str), HttpStatus.OK);
	}
	

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody Book book) throws Exception {
		return new ResponseEntity<BookDto>(this.service.update(id, book), HttpStatus.ACCEPTED);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) throws Exception {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
