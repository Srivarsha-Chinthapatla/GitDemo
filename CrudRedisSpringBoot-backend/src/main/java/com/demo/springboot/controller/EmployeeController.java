package com.demo.springboot.controller;
import com.demo.springboot.exception.ResourceNotFoundException;
import com.demo.springboot.model.*;
import com.demo.springboot.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this class becomes spring mvc rest controller and capable to handle the http request and all the rest api that we are going to build that vll define within this class
@RequestMapping("/api/employee") //give a base rest url for all restapis

public class EmployeeController {
    private static final Logger log = Logger.getLogger(Employee.class.getName());
    @Autowired
    private EmployeeRepository  employeeRepository;
    @GetMapping("/getall") // to handle http getrequest and make it a rest api
    public List<Employee> getAllEmployee() { //  getall employees restapi :handles http getrequest
        log.info("**********Getting employees data from db**********");
        return employeeRepository.findAll();
    }

    //**********Build create employee rest api ***********: handles http postrequest and it vll get a employee json object from the request and it vll convert into  employee java object and it vll save that employee java obj in db and it vll return employee saved obj to the client

    @PostMapping("/create") // to handle http postrequest and make it a rest api and post for getting resourse
    public Employee createEmployee(@RequestBody Employee employee) { //@Requestbody vll convert json to java obj
        log.info("**********Inserting a new employee record into database********** ");
        return employeeRepository.save(employee);
    }

    // ********************Build get employee byId REST API **************** // respone entity is very usefull class that we can use to construct the response of the rest api and it is a generic class so we need to pass the type employee

    @GetMapping("/read/{id}")
    @Cacheable(key = "#id", cacheNames = "employee" )
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        // To bind the value of id to method argument  we use @PATHVARIABLE Annotation
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
        log.info("**********Getting employee id from db : "+id+"**********");
        return ResponseEntity.ok(employee); // ok method internally provides 200 status code we dont have to manualy specify 200 status code as a response of this
    }

    //*********Build update employee restapi ************

    @RequestMapping (value="/update/{id}" , method=RequestMethod.PUT)// for updatinmg the resourse
    //It is used when we want to update  data into the cache.
  //  @CachePut(key = "#employeeDetails.id", cacheNames = "employee") //we have cached the return value of the method updateEmployee() in update, and id is the unique key that identifies each entry in the cache.
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.save(updateEmployee);
        log.info("**********Updating the  employee record into the  database vth id:"+id+"**********");
        return ResponseEntity.ok(updateEmployee);

    }

    //***********Build delete employee restapi***************

    @DeleteMapping("/delete/{id}")
   // @CacheEvict(key = "#id", cacheNames= "employee"  ,condition="#id<40")//It is used when we want to remove  unused data from the cache.
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : " + id));
        employeeRepository.delete(employee);
        log.info("**********Deleting the employee record from database vth id:"+id+"**********");
        return new ResponseEntity<>("deleted the record with id : "+id, HttpStatus.FOUND);

    }
}