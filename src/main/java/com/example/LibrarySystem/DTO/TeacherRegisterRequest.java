package com.example.LibrarySystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterRequest {

	//private Integer id;
	private String name;
	private String email;
	private String password;
}
