package com.example.LibrarySystem.model;


import jakarta.persistence.Column;
//import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_Book")
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Nonnull
	@Column(name = "book_id")
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String author;
	@Column(nullable = false)
	private String isbn;
	@Column(nullable = false)
	private Integer quantity;
	
	
}
