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
}
