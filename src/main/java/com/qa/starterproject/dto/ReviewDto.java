package com.qa.starterproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDto {
	
	private Long id;
	private String firstName;
	private String surname;
	private int rating;
	private String review;
//	private Book book;
	
	public ReviewDto(Long id, String firstName, String surname, int rating, String review) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.rating = rating;
		this.review = review;
	} 
}
