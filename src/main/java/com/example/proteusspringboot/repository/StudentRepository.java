package com.example.proteusspringboot.repository;

import com.example.proteusspringboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
