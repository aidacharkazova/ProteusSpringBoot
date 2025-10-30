package com.example.proteusspringboot.service;

import com.example.proteusspringboot.dto.StudentRequestDto;
import com.example.proteusspringboot.dto.StudentResponseDto;
import com.example.proteusspringboot.exception.custom.StudentNotFoundException;
import com.example.proteusspringboot.mapper.StudentMapper;
import com.example.proteusspringboot.model.Student;
import com.example.proteusspringboot.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }

    public StudentResponseDto create(StudentRequestDto dto) {
        Student student = studentMapper.toEntity(dto);
        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existing.setPassword(dto.getPassword());
        }

        Student updated = studentRepository.save(existing);
        return studentMapper.toDto(updated);
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
