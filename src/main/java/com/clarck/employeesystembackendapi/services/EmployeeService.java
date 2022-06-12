package com.clarck.employeesystembackendapi.services;

import java.util.List;

import com.clarck.employeesystembackendapi.model.Employee;

public interface EmployeeService {

  Employee createEmployee(Employee employee);

  List<Employee> getAllEmployees();
  
}
