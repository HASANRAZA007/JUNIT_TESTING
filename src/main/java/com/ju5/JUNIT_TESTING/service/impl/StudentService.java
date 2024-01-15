package com.ju5.JUNIT_TESTING.service.impl;

import com.ju5.JUNIT_TESTING.dto.StudentDto;
import com.ju5.JUNIT_TESTING.entity.Student;
import com.ju5.JUNIT_TESTING.repository.StudentRepository;
import com.ju5.JUNIT_TESTING.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService implements IStudentService {
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(ModelMapper modelMapper, StudentRepository studentRepository) {
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
       Student student=modelMapper.map(studentDto,Student.class);
       if(student==null){
           throw new NullPointerException("Student Object is Null");
       }
       else {
           Student savedStudent=studentRepository.save(student);
           return modelMapper.map(savedStudent,StudentDto.class);
       }
    }

    @Override
    public StudentDto getStudentById(long studentId) {
        if (studentId>0){
            Student student=studentRepository.getReferenceById(studentId);
            return modelMapper.map(student,StudentDto.class);

        }
        else {
            throw new NoSuchElementException("Data Not Found.");
        }

    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList=studentRepository.findAll();
        if(studentList.isEmpty()){
            throw new NoSuchElementException("No Such Student Found.");
        }
        else {
            return  Collections.singletonList(modelMapper.map(studentList, StudentDto.class));
        }
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, long id) {
        if(studentDto==null && id<0){
            throw  new NoSuchElementException("Data is Not Found.");
        }
        else {
            Student updatedStudent=studentRepository.getReferenceById(id);
            assert studentDto != null;
            updatedStudent.setFirstName(studentDto.getFirstName());
            updatedStudent.setLastName(studentDto.getLastName());
            updatedStudent.setAge(studentDto.getAge());
            updatedStudent.setDepartment(studentDto.getDepartment());
            studentRepository.save(updatedStudent);
            return modelMapper.map(updatedStudent, StudentDto.class);
        }
    }

    @Override
    public void deleteStudent(long studentId) {
        Student student=studentRepository.getReferenceById(studentId);
        if(student==null){
            throw new NoSuchElementException("Data not exist");

        }
        else {
            studentRepository.delete(student);
        }
    }
}
