package com.linuka.employeehub.service;

import com.linuka.employeehub.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long Id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long Id, Employee employee);

    List<Employee> searchEmployees(String keyword);

    void deleteEmployee(Long id);
}
