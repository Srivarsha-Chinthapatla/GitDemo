package com.demo.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity   // to make this class as a jpa entity
@Table (name="employees")           // TO Provide table details
public class Employee {
    @Id  //to make id as a primarykey
    @GeneratedValue( strategy = GenerationType.IDENTITY) //specifies how to generate values for the given column.
    //which expects values generated by an identity column in the database. This means they are auto-incremented.
    private long id;
    @Column(name="first_name")//table column names if @column is not used then jpa takes column name as firstName
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email_id")
    private String emailId;
}