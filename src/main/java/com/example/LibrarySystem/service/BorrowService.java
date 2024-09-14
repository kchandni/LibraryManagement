package com.example.LibrarySystem.service;




import org.springframework.stereotype.Service;

import com.example.LibrarySystem.DTO.StudentBorrowDetails;
import com.example.LibrarySystem.model.BorrowRecordModel;


@Service
public interface BorrowService {

	  public BorrowRecordModel borrowBook(String bookId, Integer stuId);
	    public boolean returnBook(String isbn,Integer stuId);
	    public BorrowRecordModel findBorrowingRecordByBookAndPatron(String bookId, Integer stuId);
	    public StudentBorrowDetails details(Integer stuId);
	  
}
