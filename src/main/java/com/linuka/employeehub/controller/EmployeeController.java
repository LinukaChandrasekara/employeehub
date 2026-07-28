package com.linuka.employeehub.controller;

import com.linuka.employeehub.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.linuka.employeehub.entity.Employee;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;

@Controller  //This class handles web requests
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public String listEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeService.getAllEmployees()
        );

        return "employees";
    }
    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model) {

        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "create_employee";
    }
    @PostMapping("/employees")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {

        if (result.hasErrors()){
            return "create_employee";
        }

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }
    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "edit_employee";
    }
    @PostMapping("/employees/{id}")
    public String updateEmployee(
            @PathVariable Long id,
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result) {

        if (result.hasErrors()) {
            return "edit_employee";
        }

        employeeService.updateEmployee(id, employee);

        return "redirect:/employees";
    }
    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

}
