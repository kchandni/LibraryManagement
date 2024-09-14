package com.example.LibrarySystem.model;

import java.util.Date;

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
@Table(name="Borrow")
public class BorrowRecordModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Integer borrowingRecordId;

    //Foreign Key
    //@ManyToOne
    //@JoinColumn(name = "book_id")
   // private BookModel book;
	private Integer bookId;

   // @ManyToOne
    //@JoinColumn(name = "stu_id")
    //private StudentModel student;
	private Integer studentId;
    private String email;
    //private Integer quantity;
    private Date issueDate;
    //private Date returnDate;
    private String contactInfo;
}
