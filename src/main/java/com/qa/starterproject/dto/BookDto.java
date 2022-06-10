package com.qa.starterproject.dto;


import java.util.List;

import com.qa.starterproject.domain.Review;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
	
	private Long id;
	private String title;
	private String description;
	private String author;
	private List<Review> reviews;
	
	public BookDto(Long id, String title, String description, String author) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
	}
}

