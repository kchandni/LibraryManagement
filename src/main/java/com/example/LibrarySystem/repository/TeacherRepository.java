package com.example.LibrarySystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibrarySystem.model.TeacherModel;


@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Integer> {

	Optional<TeacherModel> findByEmail(String email);
	//Optional<TeacherModel> findByPassword(String password);
}
