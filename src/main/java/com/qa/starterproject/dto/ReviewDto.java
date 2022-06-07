package com.qa.starterproject.dto;


import com.qa.starterproject.domain.Book;

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
}
