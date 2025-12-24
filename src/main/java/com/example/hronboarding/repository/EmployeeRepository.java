package com.example.hronboarding.repository;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByStatus(EmployeeStatus status);
}
