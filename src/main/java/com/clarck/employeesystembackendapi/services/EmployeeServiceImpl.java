package com.clarck.employeesystembackendapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.clarck.employeesystembackendapi.entity.EmployeeEnitity;
import com.clarck.employeesystembackendapi.model.Employee;
import com.clarck.employeesystembackendapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee createEmployee(Employee employee) {
    EmployeeEnitity employeeEnitity = new EmployeeEnitity();

    BeanUtils.copyProperties(employee, employeeEnitity);
    employeeRepository.save(employeeEnitity);

    return employee;
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<EmployeeEnitity> employeeEnitities = employeeRepository.findAll();

    List<Employee> employees = employeeEnitities
        .stream()
        .map(emp -> new Employee(
            emp.getId(),
            emp.getEmailId(),
            emp.getFirstName(),
            emp.getLastName()))
        .collect(Collectors.toList());

    return employees;
  }

}
