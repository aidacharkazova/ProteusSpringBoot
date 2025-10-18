package com.example.proteusspringboot.service;

import com.example.proteusspringboot.model.Student;
import com.example.proteusspringboot.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }


    public Student create(Student student) {
        return studentRepository.save(student);
    }


    public Student update(Long id, Student student) {
        Student studentOld = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        studentOld.setFirstName(student.getFirstName());
        studentOld.setLastName(student.getLastName());

        return studentRepository.save(studentOld);
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
