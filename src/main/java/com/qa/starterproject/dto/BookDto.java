package com.qa.starterproject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
	
	private Long id;
	private String title;
	private String description;
	private String firstName;
	private String Surname;
}
