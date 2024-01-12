package com.ju5.JUNIT_TESTING.controller;

import com.ju5.JUNIT_TESTING.dto.StudentDto;
import com.ju5.JUNIT_TESTING.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        var data=studentService.addStudent(studentDto);
        return new ResponseEntity<StudentDto>(data, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/{Id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable long Id){
        var data=studentService.getStudentById(Id);
        return new ResponseEntity<StudentDto>(data, HttpStatusCode.valueOf(200));
    }
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, long Id){
        var data=studentService.updateStudent(studentDto,Id);
        return new ResponseEntity<StudentDto>(data, HttpStatusCode.valueOf(200));
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        var data=studentService.getAllStudents();
        return new ResponseEntity<List<StudentDto>>(data, HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<StudentDto>(HttpStatusCode.valueOf(200));
    }
}
