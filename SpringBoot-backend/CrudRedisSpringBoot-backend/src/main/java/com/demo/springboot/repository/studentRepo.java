package com.demo.springboot.repository;

import com.demo.springboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface studentRepo extends JpaRepository<Student,Long> {


}
