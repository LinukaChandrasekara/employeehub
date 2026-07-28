package com.linuka.employeehub.controller;

import com.linuka.employeehub.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.linuka.employeehub.entity.Employee;
import org.springframework.ui.Model;

@Controller  //This class handles web requests
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public String listEmployees(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "firstName") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            Model model,
            Authentication authentication) {


        System.out.println(authentication.getAuthorities());

        Page<Employee> page;


        if(keyword != null && !keyword.trim().isEmpty()) {

            page = employeeService.searchEmployees(
                    keyword,
                    pageNo,
                    pageSize,
                    sortField,
                    sortDirection
            );

        } else {

            page = employeeService.getAllEmployees(
                    pageNo,
                    pageSize,
                    sortField,
                    sortDirection
            );
        }

        model.addAttribute("employees", page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        model.addAttribute("keyword", keyword);

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
