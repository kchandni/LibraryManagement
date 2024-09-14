package com.example.LibrarySystem.serviceImpl;

import java.util.Date;

import java.util.Objects;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.LibrarySystem.model.BookModel;
import com.example.LibrarySystem.model.BorrowRecordModel;
import com.example.LibrarySystem.model.StudentModel;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.repository.BorrowRepository;
import com.example.LibrarySystem.repository.StudentRepository;
import com.example.LibrarySystem.service.BookService;
import com.example.LibrarySystem.service.BorrowService;
import com.example.LibrarySystem.service.StudentService;

import com.example.LibrarySystem.DTO.StudentBorrowDetails;
import com.example.LibrarySystem.Exception.NoElementException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

	private final BorrowRepository repository;
	private final BookService bookService;
	private final BookRepository bookRepository;
	private final StudentService studentService;
	private final StudentRepository studentRepository;
	@Override
	public BorrowRecordModel borrowBook(String bookId, Integer stuId) {
		// TODO Auto-generated method stub
		//getting book model by its isbn number
		BookModel book=bookService.findById(bookId);
		//getting id of this book
		Integer bid=book.getId(); 
		//check whether book is available or not by checking its quantity
		if(book.getQuantity()>0&&book.getQuantity()<=5) 
		{
		BorrowRecordModel borrow=new BorrowRecordModel();
		//getting student by student id
		StudentModel student=studentService.findByid(stuId);
		//if student already have any book then he can't issued any other book
		if(student.isStatus()==true)
			return null; 
		
		student.setStatus(true);
		String email=student.getEmail();
		borrow.setBookId(bid);  
		borrow.setStudentId(stuId); 
		//borrow.setBook(book);
		//borrow.setStudent(student);
		borrow.setEmail(email);
		borrow.setContactInfo(student.getPhone());
        borrow.setIssueDate(new Date(System.currentTimeMillis()));
		Integer qu=book.getQuantity();
		book.setQuantity(qu-1);
		studentRepository.save(student);
		bookRepository.save(book);
		repository.save(borrow);
		return borrow;
				
		}
		throw new NoElementException("Book is temperory unavailable... Sorry for your inconvience");
		// its return null in case when quantity become 0 throws exception 
	}

	@Override
	public boolean returnBook(String isbn,Integer stuId) {
		// TODO Auto-generated method stub
		BookModel m1=bookService.findById(isbn);
		StudentModel student=studentService.findByid(stuId);
		//below line gives error if student not has this isbn book by calling findBorrowingRecordByBookAndPatron method
		BorrowRecordModel borrow=findBorrowingRecordByBookAndPatron(isbn, stuId);
		
			repository.delete(borrow);
			
		int qu=m1.getQuantity();
		m1.setQuantity(qu+1);
		student.setStatus(false);
		studentRepository.save(student);
		bookRepository.save(m1);
		//repository.save(borrow);
		
		return true;
		
	}
	
	// method to get the model which book is issued to whom(Student)
	@Override
    public  BorrowRecordModel findBorrowingRecordByBookAndPatron(String isbn, Integer stuId) 
	{
		BookModel b1= bookService.findById(isbn);
		
		Integer bookId=b1.getId();
        		
    BorrowRecordModel borrowingRecord=repository.findByStudentId(stuId)
    		.orElseThrow(()->new NoElementException("No borrow record found for this student id: " + stuId));
       // if(Objects.equals(borrowingRecord.getStudent().getId(), stuId)&&Objects.equals(borrowingRecord.getBook().getIsbn(),isbn )) 
        if(Objects.equals(borrowingRecord.getBookId(), bookId))	 
            return borrowingRecord;
        else 
        	throw new NoElementException("No borrow record found with this book id : "+isbn);
        			
    }

	//this method to getting a details which book is issued by student if he forget the isbn number of book
	@Override
	public StudentBorrowDetails details(Integer stuId) {
		// TODO Auto-generated method stub
		StudentModel studentModel=studentService.findByid(stuId);
		Optional<BorrowRecordModel> stu=repository.findByStudentId(stuId);              
		//.get();
		if(stu.isEmpty())
			throw new NoElementException("Not Borrowing any Book");
		//if (studentModel!=null&& bo!=null)
		else
    	{
			BorrowRecordModel bo=stu.get();
    		String name=studentModel.getName();
    		String con=studentModel.getPhone();
    		Integer s=studentModel.getStandard();
    		boolean f=studentModel.isStatus();
    		Date date=bo.getIssueDate();
    		Integer bid=bo.getBookId(); 
    		BookModel book=bookRepository.findById(bid).get();
    		String bookISBN=book.getIsbn();
    		String bookname=book.getName();
    		//String bookISBN=bo.getBook().getIsbn();
    		//String bookname=bo.getBook().getName();
    	return (new StudentBorrowDetails(name,con,s,f,date,bookISBN,bookname));
    	}
    	
	}	

}
