package com.qa.starterproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String author;

	@OneToMany(targetEntity = Review.class, mappedBy = "book")
	@JsonManagedReference
	private List<Review> reviews;

	public Book(Long id, String title, String description, String author) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		
	
	}

	public Book(Long id, String title, String description, String author, List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.reviews = reviews;
	}

	

}