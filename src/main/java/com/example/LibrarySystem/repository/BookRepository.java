package com.example.LibrarySystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibrarySystem.model.BookModel;


@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

	Optional<BookModel> findByisbn(String isbn);
	BookModel findByName(String isbn);
}
