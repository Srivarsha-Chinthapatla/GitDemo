package com.demo.springboot.controller;


import com.demo.springboot.exception.StudentNotFoundException;
import com.demo.springboot.model.Student;
import com.demo.springboot.repository.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.log4j.Logger;

@EnableCaching
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private Logger log = Logger.getLogger("StudentController");


    @Autowired
    private studentRepo repo;


    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    @GetMapping("/get/all")
    @Cacheable(value = "Student", key = "'id=' + #id")
    public List<Student> getAllStudents() {
        log.info("called student by id() from DB");
        return repo.findAll();
    }


    @GetMapping("get/{id}")
    @Cacheable(value = "Student", key = "'id=' + #id")

    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        //supplier is a functional interface in else throw
        Student stud = repo.findById(id).orElseThrow(() -> new StudentNotFoundException("student not found with id: " + id));
        log.info("called student by id() from DB" + id);
        return ResponseEntity.ok(stud);
    }


    @PutMapping("/update/{id}")
    @CachePut(value = "Student", key = "#id")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatestud) {
        Student updateStudent = repo.findById(id).orElseThrow(() -> new StudentNotFoundException("student not found with id: " + id));
        updateStudent.setFirstName(updatestud.getFirstName());
        updateStudent.setLastName(updatestud.getLastName());
        updateStudent.setEmail(updatestud.getEmail());
        updateStudent.setMarks(updatestud.getMarks());
        repo.save(updatestud);
        log.info("called student by id() from DB");
        return ResponseEntity.ok(updatestud);

    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id", value = "Student")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
        Student student = repo.findById(id).orElseThrow(() -> new StudentNotFoundException("student not exist with id" + id));
        repo.delete(student);
        return new ResponseEntity("deleted the record : ", HttpStatus.FOUND);


    }


}
