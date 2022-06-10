package com.qa.starterproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.starterproject.domain.Book;
import com.qa.starterproject.domain.Review;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;

	private final Long testId = 1L;
	private final String testAuthor = "prof. jeff";
	private final Book testBook = new Book(1L, "the title", "nice book", "prof. jeff");

	private final List<Review> expectedReviews = List.of(new Review(1L, "john", "doe", 4, "good"));
	private final Book testBookRev = new Book(1L, "the title", "nice book", "prof. jeff", expectedReviews);
	private final ArrayList<Book> bookArray = new ArrayList<Book>();

	@Test
	void createBookTest() throws Exception {

		RequestBuilder request = post("/book/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testBook));
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(testBook));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void readAllBookTest() throws Exception {
		bookArray.add(testBookRev);

		RequestBuilder request = get("/book/readAll").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(bookArray));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void readByIdTest() throws Exception {
		RequestBuilder request = get("/book/read/" + testId).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(testBookRev));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void readByAuthor() throws Exception {
		bookArray.add(testBookRev);
		RequestBuilder request = get("/book/author/" + testAuthor).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(bookArray));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}

	@Test
	void updateTest() throws Exception {
		final Book newTestBook = new Book(null, "new", "newer", "newest");
		final Book updatedTestBook = new Book(1L, "new", "newer", "newest", expectedReviews);

		RequestBuilder request = put("/book/update/" + testId).contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(newTestBook));
		ResultMatcher responseStatus = status().isOk();
		ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(updatedTestBook));
		this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);

	}

	@Test
	void deleteTest() throws Exception {
		RequestBuilder request = delete("/book/delete/" + testId);
		ResultMatcher responseStatus = status().isNoContent();
		this.mock.perform(request).andExpect(responseStatus);
	}
	
	@Test
	void deleteTestNotFound() throws Exception {
		RequestBuilder request = delete("/book/delete/561651651");
		ResultMatcher responseStatus = status().isNotFound();
		this.mock.perform(request).andExpect(responseStatus);
	}

}
