package com.qa.starterproject.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.starterproject.domain.Book;

@SpringBootTest
@ActiveProfiles("dev")
@Sql(scripts = {"classpath:init.sql", "classpath:schema.sql"},executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BookRepoTest {
	
	@Autowired
	private BookRepo repo;

	@Test
	public void findAll() {
		List<Book> book = repo.findAll();
		assertThat(book).isNotNull();
		
	}
	
}
