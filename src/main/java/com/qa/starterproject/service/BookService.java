package com.qa.starterproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.starterproject.domain.Book;
import com.qa.starterproject.dto.BookDto;
import com.qa.starterproject.exception.BookException;
import com.qa.starterproject.repo.BookRepo;

@Service
public class BookService {

	private ModelMapper mapper;

	private BookRepo repo;

	public BookService(BookRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	private BookDto mapToDto(Book car) {
		return this.mapper.map(car, BookDto.class);
	}

	// create book
	public BookDto create(Book book) {
		return this.mapToDto(this.repo.save(book));
	}

	// read book
	public List<BookDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// read book by id
	public BookDto readId(Long id) throws BookException {
		return this.mapToDto(this.repo.findById(id).orElseThrow(BookException::new));
	}

	// update book
	public BookDto update(Long id, Book book) throws BookException {
		Book exists = this.repo.findById(id).orElseThrow(BookException::new);
		exists.setTitle(book.getTitle());
		exists.setDescription(book.getDescription());
		exists.setFirstName(book.getFirstName());
		exists.setSurname(book.getSurname());
		return this.mapToDto(this.repo.saveAndFlush(exists));
	}

	// delete book
	public boolean delete(Long id) throws BookException {
		this.repo.findById(id).orElseThrow(BookException::new);
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	// find book by title
	public List<BookDto> findByTitle(String str) {
		return this.repo.findByTitle(str).stream().map(this::mapToDto).collect(Collectors.toList());
	}

}
