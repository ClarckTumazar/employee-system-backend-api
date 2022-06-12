package com.clarck.employeesystembackendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clarck.employeesystembackendapi.entity.EmployeeEnitity;

public interface EmployeeRepository extends JpaRepository<EmployeeEnitity, Long>{
  
}
