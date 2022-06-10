package com.qa.starterproject.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book does not exist")
public class BookException extends EntityNotFoundException {

	private static final long serialVersionUID = -2060531372042955897L;

}
