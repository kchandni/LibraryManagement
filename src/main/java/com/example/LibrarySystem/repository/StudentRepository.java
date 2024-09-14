package com.example.LibrarySystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibrarySystem.model.StudentModel;


@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer>{

	Optional<StudentModel> findByEmail(String email);
	Optional<StudentModel> findByName(StudentModel model);
}
