package com.example.LibrarySystem.serviceImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibrarySystem.Exception.NoElementException;
import com.example.LibrarySystem.model.BookModel;
import com.example.LibrarySystem.repository.BookRepository;
import com.example.LibrarySystem.service.BookService;


@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private  BookRepository repo;
	
	// To see all books
	@Override
	public List<BookModel> findAllBooks() {
		
		
		return (List<BookModel>) repo.findAll();
	}

	// To see one particular book through its isbn number.
	@Override
	public BookModel findById(String isbn) {
		// TODO Auto-generated method stub
		Optional<BookModel> model=repo.findByisbn(isbn);
		if(model.isPresent())
		{
			BookModel modelById=model.get();
			repo.save(modelById);
			return modelById;
			
		}
		throw new NoElementException("Book is not available with this isbn number: "+isbn);
	}

	//To delete a book through its isbn number.
	@Override
	public String deleteBook(String isbn) {
		// TODO Auto-generated method stub
		Optional<BookModel> book=repo.findByisbn(isbn);
		if(book.isPresent())
		{
			Integer idInteger=book.get().getId();
			repo.deleteById(idInteger);
			return "Book Suucessfully Deleted";
		}
		throw new NoElementException("Book is not available with this isbn number: "+isbn);
		//return "Book is not found";
	}

	// To delete all books
	@Override
	public String deleteAllBook() {
		// TODO Auto-generated method stub
		repo.deleteAll();
		return "Deleted All";
	}

	
	@Override
	public String addBook(BookModel model) {
		// TODO Auto-generated method stub
		repo.save(model);
		return "Book Successfully Added";
	}


}
