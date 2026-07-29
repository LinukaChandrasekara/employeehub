package com.linuka.employeehub.dto;

public class DepartmentStats {

    private String department;
    private Long employeeCount;

    public DepartmentStats(String department, Long employeeCount) {
        this.department = department;
        this.employeeCount = employeeCount;
    }

    public String getDepartment() {
        return department;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }
}