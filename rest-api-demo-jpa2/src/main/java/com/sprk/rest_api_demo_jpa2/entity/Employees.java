package com.sprk.rest_api_demo_jpa2.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employees extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String empId;

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 15)
    private String phone;

    private String department;

    private String  designation;

    private LocalDate dateOfJoining;
}
