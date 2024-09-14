package com.example.LibrarySystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LibrarySystem.model.BorrowRecordModel;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecordModel, Integer>{

	Optional<BorrowRecordModel> findByStudentId(Integer stu);
	Optional<BorrowRecordModel> findByBookId(Integer book);
}
