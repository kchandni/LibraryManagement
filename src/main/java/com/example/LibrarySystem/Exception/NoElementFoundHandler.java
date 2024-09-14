package com.example.LibrarySystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class NoElementFoundHandler {

	@ExceptionHandler(value=NoElementException.class)
	public ResponseEntity<Object> handlerException(NoElementException idNotFoundException)
	{
		NoElementFoundException idException=new NoElementFoundException(idNotFoundException.getMessage(),idNotFoundException.getCause(),HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(idException,HttpStatus.NOT_FOUND);
	

   }
}
