package com.linuka.employeehub.service;

import com.linuka.employeehub.entity.Employee;
import com.linuka.employeehub.exception.EmployeeNotFoundException;
import com.linuka.employeehub.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@Service  //This class contains business logic and should be managed as a Spring bean
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }

    @Override
    public Page<Employee> getAllEmployees(
            int pageNo,
            int pageSize,
            String sortField,
            String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();


        Pageable pageable =
                PageRequest.of(pageNo, pageSize, sort);


        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(Long Id) {
        return employeeRepository.findById(Id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + Id + " was not found"
                        ));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " was not found"
                        ));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setPosition(employee.getPosition());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setHireDate(employee.getHireDate());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee with ID " + id + " was not found"
                        ));

        employeeRepository.delete(employee);
    }
    @Override
    public Page<Employee> searchEmployees(
            String keyword,
            int pageNo,
            int pageSize,
            String sortField,
            String sortDirection) {


        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();


        Pageable pageable =
                PageRequest.of(pageNo, pageSize, sort);


        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        keyword,
                        pageable
                );
    }
}
