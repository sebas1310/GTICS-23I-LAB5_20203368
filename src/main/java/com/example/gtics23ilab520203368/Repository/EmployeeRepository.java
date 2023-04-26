package com.example.gtics23ilab520203368.Repository;

import com.example.gtics23ilab520203368.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {

}
