package com.example.LibrarySystem.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.LibrarySystem.DTO.TeacherRegisterRequest;
import com.example.LibrarySystem.Exception.NoElementException;
import com.example.LibrarySystem.model.TeacherModel;
import com.example.LibrarySystem.model.UserRole;
import com.example.LibrarySystem.repository.TeacherRepository;
import com.example.LibrarySystem.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{
	
	private final PasswordEncoder passwordEncoder;
	private final TeacherRepository repository;
	@Override
	public String register(TeacherRegisterRequest request)
	{
		TeacherModel user1 = TeacherModel.builder()
				//.id(request.getId())
				.name(request.getName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.userRole(UserRole.TEACHER)
				.build();
		
		
	 repository.save(user1);
	 return "Registered";
	}

	
	@Override
	public String deleteAll()
	{
		repository.deleteAll();
		return "deleted";
	}

	@Override
	public List<TeacherModel> findAllTeacher() {
		// TODO Auto-generated method stub
		return (List<TeacherModel>) repository.findAll();
	}

	@Override
	public TeacherModel findById(Integer Id) {
		Optional<TeacherModel> model=repository.findById(Id);
		if(model.isPresent())
		{
			TeacherModel modelById=model.get();
			return repository.save(modelById);
			
		}
		throw new NoElementException("teacher is not availablein our database with this id: "+Id);
		//return null;
	}

	@Override
	public void deleteTeacher(Integer id) {
		// TODO Auto-generated method stub
		if(repository.existsById(id))
		{
			repository.deleteById(id);
			System.out.println("Deleted");
		}
		else
			throw new NoElementException("teacher is not availablein our database with this id: "+id);
		//System.out.println("id not found");
	}


}
