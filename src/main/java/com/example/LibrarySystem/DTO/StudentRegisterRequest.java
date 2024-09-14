package com.example.LibrarySystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterRequest {

	//private Integer id;
	private String name;
	private String email;
	private String password;
	private Integer standard;
	private String phone;
}
