package com.demo.springboot.repository;

import com.demo.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// We no need to add @repository because spring datajpa internally takes care of adding @repository annotation to jparepository implementation class
public interface EmployeeRepository extends JpaRepository<Employee, Long> {// jparepo takes 2 para type of jpa entity and type of primarykey
    // as employeerepo extends jparepo it gets all crud database methods

}
