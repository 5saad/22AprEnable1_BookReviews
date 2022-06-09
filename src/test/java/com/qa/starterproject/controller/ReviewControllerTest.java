package com.qa.starterproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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
@Sql(scripts = {"classpath:testschema.sql","classpath:testdata.sql"},executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class ReviewControllerTest {
	
	@Autowired
    private MockMvc mock;

    @Autowired
    private ObjectMapper mapper;
    
   
    private final Long testId = 1L;
    private final int testRating = 4;
    private final Book testBook = new Book(1L,"the title", "nice review", "prof. jeff");
    private final Review testReview = new Review(1L,"john","doe",4,"good",testBook);
  
    private final ArrayList<Review> reviewArray = new ArrayList<Review>();
    

    
    
    @Test
    void createReviewTest() throws Exception {
    	
    	RequestBuilder request = post("/review/create").contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(testReview));
    	ResultMatcher responseStatus = status().isCreated();
    	ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(testReview));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    }
 
 
    
    @Test
    void readAllReviewTest() throws Exception { 
    	reviewArray.add(testReview);
    	
    	RequestBuilder request = get("/review/readAll").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    	ResultMatcher responseStatus = status().isOk();
    	ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(reviewArray));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    }
    
    @Test
    void readByIdTest() throws Exception {
    	RequestBuilder request = get("/review/read/"+testId).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    	ResultMatcher responseStatus = status().isOk();
    	ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(testReview));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    }
    
    @Test
    void readByRating() throws Exception {
    	reviewArray.add(testReview);
    	RequestBuilder request = get("/review/rating/"+testRating).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    	ResultMatcher responseStatus = status().isOk();
    	ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(reviewArray));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    }
    
    @Test
    void updateTest() throws Exception {
    	final Review newTestReview = new Review(null,"myname", "jeff", 2, "meh");
    	final Review updatedTestReview = new Review(1L,"myname", "jeff", 2, "meh",testBook);
    	
    	RequestBuilder request = put("/review/update/"+testId).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(newTestReview));
    	ResultMatcher responseStatus = status().isAccepted();
    	ResultMatcher responseContent = content().json(this.mapper.writeValueAsString(updatedTestReview));
    	this.mock.perform(request).andExpect(responseStatus).andExpect(responseContent);
    	
    	
    }
    
    @Test
    void deleteTest() throws Exception {
    	RequestBuilder request = delete("/review/delete/"+testId);
    	ResultMatcher responseStatus = status().isNoContent();
    	this.mock.perform(request).andExpect(responseStatus);
    }  
	
}
