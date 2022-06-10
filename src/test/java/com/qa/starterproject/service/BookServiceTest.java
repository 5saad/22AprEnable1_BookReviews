package com.qa.starterproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.starterproject.domain.Book;
import com.qa.starterproject.dto.BookDto;
import com.qa.starterproject.repo.BookRepo;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService service;

	@MockBean
	private BookRepo repo;

	@Test
	public void readAllTest() {
		List<BookDto> expectedBooksDto = List.of(new BookDto(1L, "cool title", "random description", "an author"),
				new BookDto(2L, "cool title II", "random description", "an author"));
		List<Book> expectedBooks = List.of(new Book(1L, "cool title", "random description", "an author"),
				new Book(2L, "cool title II", "random description", "an author"));

		Mockito.when(this.repo.findAll()).thenReturn(expectedBooks);
		assertThat(this.service.readAll()).isEqualTo(expectedBooksDto);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void readByIdTest() {
		final Optional<Book> book = Optional.of(new Book(1L, "title", "great book", "John Doe"));
		final BookDto bookDto = new BookDto(1L, "title", "great book", "John Doe");
		final long id = 1L;

		Mockito.when(this.repo.findById(id)).thenReturn(book);
		assertThat(this.service.readId(id)).isEqualTo(bookDto);
	}

	@Test
	public void createTest() {
		final Book created = new Book(1L, "title", "great book", "John Doe");
		final BookDto createdDto = new BookDto(1L, "title", "great book", "John Doe");

		Mockito.when(this.repo.save(created)).thenReturn(created);
		assertThat(this.service.create(created)).isEqualTo(createdDto);

		Mockito.verify(this.repo, Mockito.times(1)).save(created);
	}

	@Test
	public void updateTest() {
		final Optional<Book> book = Optional.of(new Book(1L, "title", "great book", "John Doe"));
		final Book updated = new Book(1L, "changed", "changed", "changed");
		final BookDto updatedBookDto = new BookDto(1L, "changed", "changed", "changed");
		final long id = 1L;

		Mockito.when(this.repo.findById(id)).thenReturn(book);
		Mockito.when(this.repo.saveAndFlush(updated)).thenReturn(updated);

		assertThat(this.service.update(id, updated)).isEqualTo(updatedBookDto);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(updated);

	}

	@Test
	public void deleteTest() {
		final long id = 1L;
		Optional<Book> foundBook = Optional.of(new Book(1L, "title", "great book", "John Doe"));
		Mockito.when(this.repo.findById(id)).thenReturn(foundBook);
		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.delete(id)).isTrue();

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void findByAuthorTest() {
		List<Book> expectedBook = List.of(new Book(1L, "cool title", "random description", "an author"));
		List<BookDto> expectedBookDto = List.of(new BookDto(1L, "cool title", "random description", "an author"));
		final String author= "author";
		
		Mockito.when(this.repo.findByAuthorContains(author)).thenReturn(expectedBook);
		assertThat(this.service.findByAuthor(author)).isEqualTo(expectedBookDto);
		
		Mockito.verify(this.repo, Mockito.times(1)).findByAuthorContains(author);
	}

}
