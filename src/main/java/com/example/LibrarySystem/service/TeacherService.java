package com.example.LibrarySystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.LibrarySystem.DTO.TeacherRegisterRequest;
import com.example.LibrarySystem.model.TeacherModel;

@Service
public interface TeacherService {

	
	public String register(TeacherRegisterRequest request);
	
	public String deleteAll();
	public List<TeacherModel> findAllTeacher();
    public TeacherModel findById(Integer Id);
    public void deleteTeacher(Integer id);
    
}
