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
    EmployeeEnitity employeeEnitity 
        = new EmployeeEnitity();

    BeanUtils.copyProperties(employee, employeeEnitity);
    employeeRepository.save(employeeEnitity);

    return employee;
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<EmployeeEnitity> employeeEnitities
        = employeeRepository.findAll();

    List<Employee> employees = employeeEnitities
        .stream()
        .map(emp -> new Employee(
            emp.getId(),
            emp.getFirstName(),
            emp.getLastName(),
            emp.getEmailId()))
        .collect(Collectors.toList());

    return employees;
  }

  @Override
  public boolean deleteEmployee(Long id) {
    EmployeeEnitity employee 
        = employeeRepository.findById(id).get(); 
    employeeRepository.delete(employee);
    return true;
  }

  @Override
  public Employee getEmployeeById(Long id) {
    EmployeeEnitity employeeEnitity
        = employeeRepository.findById(id).get();
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeEnitity, employee);
    return employee;
  }

  @Override
  public Employee updateEmployee(Long id, Employee employee) {
    EmployeeEnitity employeeEnitity 
        = employeeRepository.findById(id).get();

    employeeEnitity.setEmailId(employee.getEmailId());
    employeeEnitity.setFirstName(employee.getFirstName());
    employeeEnitity.setLastName(employee.getLastName());

    employeeRepository.save(employeeEnitity);
    return employee;
  }

}
