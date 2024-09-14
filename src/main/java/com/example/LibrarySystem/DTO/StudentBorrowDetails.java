package com.example.LibrarySystem.DTO;




import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentBorrowDetails {

	private String name;
	private String Contact;
	private Integer Standard;
	private boolean status;
	private Date issuedDate;
	private String bookISBN;
	private String bookName;
	
}
