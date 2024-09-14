package com.example.LibrarySystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibrarySystem.DTO.AuthenticateRequest;
import com.example.LibrarySystem.DTO.AuthenticateResponse;
import com.example.LibrarySystem.DTO.StudentBorrowDetails;
import com.example.LibrarySystem.DTO.StudentRegisterRequest;

import com.example.LibrarySystem.model.BookModel;
import com.example.LibrarySystem.model.BorrowRecordModel;
import com.example.LibrarySystem.model.StudentModel;


import com.example.LibrarySystem.service.BookService;
import com.example.LibrarySystem.service.BorrowService;
import com.example.LibrarySystem.service.StudentService;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library/student")
public class StudentController {

	private final StudentService service;
	private final BookService bookService;
	Authentication authentication;
	StudentModel model;
	//private final StudentRepository repository;
	private final BorrowService borrowService;
	
	
	@PostMapping("/register")
    public String register(@RequestBody StudentRegisterRequest request) {
		return (service.register(request));
  
    }
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request)
	{
		
		return ResponseEntity.ok(service.authenticate(request));
		
	}
    @GetMapping("/getDetails")
    public ResponseEntity<StudentBorrowDetails> details(@RequestParam Integer stuId)
    {
    	
    	StudentBorrowDetails stuDe=borrowService.details(stuId);
    	if(stuDe!=null)
    		return ResponseEntity.ok(stuDe);
    	else
    		return ResponseEntity.notFound().build();
    		
    }
	@GetMapping("/bookIssued")
	 public ResponseEntity<String> borrowBook(@RequestParam String isbn, @RequestParam Integer stuId) 
	{
        // student can issued only one book at a time.
        BorrowRecordModel borrowingRecord = borrowService.borrowBook(isbn, stuId);

       if (borrowingRecord == null) {
           return ResponseEntity.badRequest().body("Can't issued book please check the following reason..\n1. This Student already has some book please return it then come again");      //("Book is not available");
       }
       return ResponseEntity.ok("Book borrowed successfully");

   }
	
	@PutMapping("/returnBook")
	public ResponseEntity<String> returnBook(@RequestParam String isbn,@RequestParam Integer stuId)
	{
		if(borrowService.returnBook(isbn,stuId))
		{
		return ResponseEntity.ok("Successfully Book Returned");
		}
		else
			return ResponseEntity.notFound().build();
			//return ResponseEntity.badRequest().body("Can't Returned...Please check the inputs");
		
	}
	
	@GetMapping("/findAll")
	public List<BookModel> findAllBook()
	{
		return bookService.findAllBooks();
	}
	@GetMapping("/findOne")
	public BookModel findOneBook( @RequestParam String isbn)
	{
		return bookService.findById(isbn);
	}
}
