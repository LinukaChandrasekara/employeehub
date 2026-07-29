package com.linuka.employeehub.dto;

import java.math.BigDecimal;

public class SalaryStats {

    private String range;
    private Long employeeCount;

    public SalaryStats(String range, Long employeeCount) {
        this.range = range;
        this.employeeCount = employeeCount;
    }

    public String getRange() {
        return range;
    }

    public Long getEmployeeCount() {
        return employeeCount;
    }
}