package com.ju5.JUNIT_TESTING.service;

import com.ju5.JUNIT_TESTING.dto.StudentDto;
import com.ju5.JUNIT_TESTING.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {
    StudentDto addStudent(StudentDto studentDto);
    StudentDto getStudentById(long studentId);
    List<StudentDto> getAllStudents();
    StudentDto updateStudent(StudentDto studentDto,long Id);
    void deleteStudent(long studentId);
}
