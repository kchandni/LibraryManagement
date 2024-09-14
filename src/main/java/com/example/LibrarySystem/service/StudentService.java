package com.example.LibrarySystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.LibrarySystem.DTO.AuthenticateRequest;
import com.example.LibrarySystem.DTO.AuthenticateResponse;
import com.example.LibrarySystem.DTO.StudentRegisterRequest;
import com.example.LibrarySystem.model.StudentModel;

@Service
public interface StudentService {

	public List<StudentModel> findAllStudent();
    public StudentModel findByid(Integer Id);
    public void deleteStudent(Integer id);
    public void deleteAllStudent();
    public String register(StudentRegisterRequest request);
    AuthenticateResponse authenticate(AuthenticateRequest request);
   
}
