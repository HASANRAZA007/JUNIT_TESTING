package com.ju5.JUNIT_TESTING.service.impl;

import com.ju5.JUNIT_TESTING.dto.StudentDto;
import com.ju5.JUNIT_TESTING.entity.Student;
import com.ju5.JUNIT_TESTING.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStudent() {
        StudentDto studentDto = new StudentDto("John", "Doe", 25, "Computer Science");
        Student studentEntity = new Student(1L,"John", "Doe", 25, "Computer Science");

        when(modelMapper.map(studentDto, Student.class)).thenReturn(studentEntity);
        when(studentRepository.save(studentEntity)).thenReturn(studentEntity);
        when(modelMapper.map(studentEntity, StudentDto.class)).thenReturn(studentDto);

        StudentDto result = studentService.addStudent(studentDto);

        assertNotNull(result);

        verify(modelMapper, times(1)).map(studentDto, Student.class);
        verify(studentRepository, times(1)).save(studentEntity);
        verify(modelMapper, times(1)).map(studentEntity, StudentDto.class);
    }

    @Test
    void testGetStudentByIdValidId() {

        long validStudentId = 1L;
        Student studentEntity = new Student(validStudentId, "John", "Doe", 25, "Computer Science");

        when(studentRepository.getReferenceById(validStudentId)).thenReturn(studentEntity);
        when(modelMapper.map(studentEntity, StudentDto.class)).thenReturn(new StudentDto("John", "Doe", 25, "Computer Science"));

        StudentDto result = studentService.getStudentById(validStudentId);
        assertNotNull(result);
        verify(studentRepository, times(1)).getReferenceById(validStudentId);
        verify(modelMapper, times(1)).map(studentEntity, StudentDto.class);
    }

    @Test
    void testGetStudentByIdInvalidId() {
        long invalidStudentId = 0L;

        when(studentRepository.getReferenceById(invalidStudentId)).thenReturn(null);

        NoSuchElementException exception =  assertThrows(NoSuchElementException.class, () -> studentService.getStudentById(invalidStudentId));

        assertEquals("Data Not Found.", exception.getMessage());
    }

    @Test
    void getAllStudents() {
        List<StudentDto> studentDtoList=new ArrayList<StudentDto>();
        List<Student> studentList=new ArrayList<Student>();
        when(studentRepository.findAll()).thenReturn(studentList);
        when( Collections.singletonList(modelMapper.map(studentList, StudentDto.class))).thenReturn(studentDtoList);
        assertNotNull(studentDtoList);
    }

    @Test
    void updateStudent() {
        long studentId = 1L;
        StudentDto updatedStudentDto = new StudentDto("John", "Doe", 25, "Computer Science");

        Student existingStudent = new Student(1L,"Hasan","Raza",26,"Management Sciences"); // Initialize with appropriate data
        when(studentRepository.getReferenceById(studentId)).thenReturn(existingStudent);

        StudentDto result = studentService.updateStudent(updatedStudentDto, studentId);

        verify(studentRepository, times(1)).getReferenceById(studentId);
        verify(studentRepository, times(1)).save(existingStudent);

        assertEquals(updatedStudentDto.getFirstName(), result.getFirstName());
        assertEquals(updatedStudentDto.getLastName(), result.getLastName());
        assertEquals(updatedStudentDto.getAge(), result.getAge());
        assertEquals(updatedStudentDto.getDepartment(), result.getDepartment());
    }

    @Test
    void deleteStudent() {
        long validStudentId = 1L;
        Student studentEntity = new Student(validStudentId, "John", "Doe", 25, "Computer Science");

        when(studentRepository.getReferenceById(validStudentId)).thenReturn(studentEntity);
         studentRepository.delete(studentEntity);
        when(modelMapper.map(studentEntity, StudentDto.class)).thenReturn(new StudentDto("John", "Doe", 25, "Computer Science"));

        StudentDto result = studentService.getStudentById(validStudentId);
        assertNotNull(result);
        verify(studentRepository, times(1)).getReferenceById(validStudentId);
        verify(modelMapper, times(1)).map(studentEntity, StudentDto.class);
    }
}