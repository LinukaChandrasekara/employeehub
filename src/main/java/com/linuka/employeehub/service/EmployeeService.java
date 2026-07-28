package com.linuka.employeehub.service;

import com.linuka.employeehub.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Page<Employee> getAllEmployees(
            int pageNo,
            int pageSize,
            String sortField,
            String sortDirection
    );

    Employee getEmployeeById(Long Id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long Id, Employee employee);

    Page<Employee> searchEmployees(
            String keyword,
            int pageNo,
            int pageSize,
            String sortField,
            String sortDirection
    );

    void deleteEmployee(Long id);
}
