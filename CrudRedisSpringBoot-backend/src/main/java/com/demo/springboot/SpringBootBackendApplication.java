package com.demo.springboot;

import com.demo.springboot.repository.EmployeeRepository;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class SpringBootBackendApplication implements  CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;
	 static Logger log = Logger.getLogger(SpringBootBackendApplication.class.getName());

	public static void main(String[] args) {

        BasicConfigurator.configure();
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
