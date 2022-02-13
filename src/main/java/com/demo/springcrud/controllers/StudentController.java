package com.demo.springcrud.controllers;

import com.demo.springcrud.model.Student;
import com.demo.springcrud.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")

public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find any student with given id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //created 201
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent){
        Student existingStudent=studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Could not find any student with given id: " + id));
        existingStudent.setGrade(updatedStudent.getGrade());
        existingStudent.setName(updatedStudent.getName());
        return studentRepository.save(existingStudent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //NO_CONTENT 204
    public void deleteById(@PathVariable Long id){
        studentRepository.deleteById(id);
    }

}
