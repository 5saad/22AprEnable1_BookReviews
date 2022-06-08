package com.qa.starterproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
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
import com.qa.starterproject.dto.BookDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@Sql(scripts = {"classpath:init.sql", "classpath:schema.sql"},executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
    private MockMvc mock;

    @Autowired
    private ModelMapper modelMapper;
	
	private BookDto mapToDTO(Book book) {
        return this.modelMapper.map(book, BookDto.class);
    }

    @Autowired
    private ObjectMapper jsonifier;
    
    private final Book TEST_BOOK = new Book(1L,"Barry", "blue", "puddle");
    
    @Test
    void testCreateBook() throws Exception {
    	
    	RequestBuilder request = post("/book/create").contentType(MediaType.APPLICATION_JSON).content(this.jsonifier.writeValueAsString(TEST_BOOK));
    	ResultMatcher responseStatus = status().isCreated();
    	ResultMatcher responseContent = content().json(this.jsonifier.writeValueAsString(TEST_BOOK));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    }
	
}
    


