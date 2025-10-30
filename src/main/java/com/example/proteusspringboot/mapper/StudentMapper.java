package com.example.proteusspringboot.mapper;


import com.example.proteusspringboot.dto.StudentRequestDto;
import com.example.proteusspringboot.dto.StudentResponseDto;
import com.example.proteusspringboot.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentRequestDto studentRequestDto);
    StudentResponseDto toDto(Student student);
}
