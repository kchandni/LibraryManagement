package com.example.LibrarySystem.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LibrarySystem.DTO.AuthenticateRequest;
import com.example.LibrarySystem.DTO.AuthenticateResponse;
import com.example.LibrarySystem.DTO.StudentRegisterRequest;
import com.example.LibrarySystem.Exception.NoElementException;
import com.example.LibrarySystem.config.JWTService;
import com.example.LibrarySystem.model.StudentModel;
import com.example.LibrarySystem.model.UserRole;
import com.example.LibrarySystem.repository.StudentRepository;
import com.example.LibrarySystem.service.StudentService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

	private final JWTService jwtService;
	private final StudentRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager manager;
	
	
	@Override
	public List<StudentModel> findAllStudent() {
		
		return (List<StudentModel>) repository.findAll();
	}

	@Override
	public StudentModel findByid(Integer Id) {
		
		Optional<StudentModel> model=repository.findById(Id);
		if(model.isPresent())
		{
			StudentModel modelById=model.get();
			repository.save(modelById);
			return modelById;
					
			
		}
		throw new NoElementException("Student is not available in our database with this id: "+Id);
		//return null;
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		if(repository.existsById(id))
		{
			repository.deleteById(id);
			System.out.println("Deleted");
		}
		else
			throw new NoElementException("Student is not available in our database with this id: "+id);
		//System.out.println("id not found");
	}

	@Override
	public void deleteAllStudent() {
		// TODO Auto-generated method stub
		 repository.deleteAll();
	}

	@Override
	public String register(StudentRegisterRequest request) {
		// TODO Auto-generated method stub
		Optional<StudentModel> existingUser = repository.findByEmail(request.getEmail());
		if(existingUser.isPresent())
			throw new NoElementException("This Email is already exits in our database");
		 StudentModel user1 = StudentModel.builder()
				 //.id(request.getId())
					.name(request.getName())
					.email(request.getEmail())
					.standard(request.getStandard())
					.phone(request.getPhone())
					.password(passwordEncoder.encode(request.getPassword()))
					.userRole(UserRole.STUDENT)
					.build();
			
			
		 repository.save(user1);
		 return "Student Successfully Registered";
		
	}

	@Override
	public AuthenticateResponse authenticate(AuthenticateRequest request) {
		// TODO Auto-generated method stub
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		StudentModel user1=repository.findByEmail(request.getEmail())
				.orElseThrow();
		
		String jwtToken = jwtService.generateToken(user1);
		return AuthenticateResponse.builder()
				.token(jwtToken)
				.build();
		
	}

	


}
