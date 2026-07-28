package com.linuka.employeehub.service;

import com.linuka.employeehub.entity.Employee;
import com.linuka.employeehub.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //This class contains business logic and should be managed as a Spring bean
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long Id) {
        return employeeRepository.findById(Id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElse(null);

        if(existingEmployee != null) {

            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setPosition(employee.getPosition());
            existingEmployee.setSalary(employee.getSalary());

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
