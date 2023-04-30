package com.example.gtics23ilab520203368.Repository;

import com.example.gtics23ilab520203368.Entity.Employees;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {

    @Query(nativeQuery = true, value ="SELECT e.*, j.job_title\n" +
            "FROM employees e\n" +
            "JOIN jobs j ON e.job_id = j.job_id\n" +
            "JOIN departments d ON e.department_id = d.department_id\n" +
            "JOIN locations l ON d.location_id = l.location_id\n" +
            "WHERE e.first_name LIKE ?1 \n" +
            "OR e.last_name LIKE ?1 \n" +
            "OR j.job_title LIKE ?1 \n" +
            "OR l.city LIKE ?1")
    List<Employees> search(String txt);

    @Modifying
    @Query(nativeQuery = true, value = "delete from employees where employee_id=?1")
    void deleteEmployee(Integer id);

    @Query(nativeQuery = true, value = "select * from employees where manager_id=100 or manager_id=101 or manager_id=103 or manager_id IS NULL")
    List<Employees> showBoss();

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO employees(first_name, last_name, email, password, hire_date, job_id, salary, manager_id, department_id, enabled) values (?1,?2,?3,?4,NOW(),?5,?6,?7,?8,1)")
    void newEmployee(String name, String last_name, String email, String password, String jobid, BigDecimal salary, Integer manid, Integer department_id);
}
