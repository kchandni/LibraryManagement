package com.example.LibrarySystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LibrarySystem.model.BookModel;




@Service

public interface BookService {



    public List<BookModel> findAllBooks();
    public BookModel findById(String isbn);
    public String deleteBook(String isbn);
    public String deleteAllBook();
    public String addBook(BookModel model);
    //public boolean canBorrow(String isbn);
    //public boolean borrow(String isbn);
    //public boolean returnBook(String isbn);
   
	
}
