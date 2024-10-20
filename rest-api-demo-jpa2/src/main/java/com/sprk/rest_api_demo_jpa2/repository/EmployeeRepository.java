package com.sprk.rest_api_demo_jpa2.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprk.rest_api_demo_jpa2.entity.Employees;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    Optional<Employees> findByEmail(String email);
    Optional<Employees> findByPhone(String phone);
    Optional<Employees> findByEmpId(String empId);

    @Transactional
    @Modifying
    @Query("update Employees set firstName = :fname, lastName = :lName, email = :email, phone = :phone, department = :dept, designation = :design where empId = :empId")
    void updateEmployee(String fname, String lName, String email, String phone, String dept,
            String design, String empId);

}
