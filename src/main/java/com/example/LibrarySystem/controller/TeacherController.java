package com.example.LibrarySystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibrarySystem.DTO.TeacherRegisterRequest;
import com.example.LibrarySystem.model.BookModel;
import com.example.LibrarySystem.model.StudentModel;
import com.example.LibrarySystem.model.TeacherModel;
import com.example.LibrarySystem.service.BookService;
import com.example.LibrarySystem.service.StudentService;
import com.example.LibrarySystem.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library/admin")
public class TeacherController {

	private final TeacherService service;
	private final BookService bookService;
	private final StudentService studentService;
	
	@PostMapping("/register")
	public String register(@RequestBody TeacherRegisterRequest request)
	{
		return (service.register(request));
	}
	
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody BookModel model)
	{
		
		return bookService.addBook(model);
	}
	@DeleteMapping("/deleteBook")
	public String deleteByIsbn(@RequestParam String isbn)
	{
		return bookService.deleteBook(isbn);
	}
	@DeleteMapping("/deleteAllBook")
	public String deleteAll()
	{
		return bookService.deleteAllBook();
	}
	@GetMapping("/getOneStu")
	public StudentModel findOneStu( @RequestParam Integer id)
	{
		return studentService.findByid(id);
	}
	@DeleteMapping("/deleteAllStu")
	public void deleteStudent()
	{
		studentService.deleteAllStudent();
	}
	@DeleteMapping("/deleteOneStu")
	public void deleteOneStu(@RequestParam Integer id)
	{
		studentService.deleteStudent(id);
	}
	@GetMapping("/getAllStu")
	public List<StudentModel> get()
	{
		return studentService.findAllStudent();
	}
	@GetMapping("/getAllTeacher")
	public List<TeacherModel> getTeacher()
	{
		return service.findAllTeacher();
	}
	@GetMapping("/getOneTeacher")
	public TeacherModel findOneTeacher( @RequestParam Integer id)
	{
		return service.findById(id);
	}
	@DeleteMapping("/deleteAllTeacher")
	public String deleteTeacher()
	{
		return service.deleteAll();
	}
	@DeleteMapping("/deleteOneTeacher")
	public void deleteOneTeacher(@RequestParam Integer id)
	{
		service.deleteTeacher(id);
	}
	
}
