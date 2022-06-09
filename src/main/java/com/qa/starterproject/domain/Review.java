package com.qa.starterproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Review {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String surname;
	private int rating;
	private String review;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	@JoinColumn(name="book_id")
    private Book book;

	public Review(Long id, String firstName, String surname, int rating, String review) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.rating = rating;
		this.review = review;
	}

	public Review(Long id, String firstName, String surname, int rating, String review, Book book) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.rating = rating;
		this.review = review;
		this.book = book;
	}

	

	
	
	
	
	
}
