// WHY INTERFACE INSTEAD OF A CLASS?

// JpaRepository is an interface, and Spring Data JPA creates the implementation for you at runtime.
// That means you define what operations you need, and Spring provides how they're implemented.

package com.linuka.employeehub.repository;

import com.linuka.employeehub.dto.DepartmentStats;
import com.linuka.employeehub.dto.SalaryStats;
import com.linuka.employeehub.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
            String firstName,
            String lastName,
            String email,
            String department,
            Pageable pageable
    );
    long countBy();

    @Query("SELECT COUNT(DISTINCT e.department) FROM Employee e")
    long countDistinctDepartments();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    BigDecimal averageSalary();

    @Query("""
       SELECT new com.linuka.employeehub.dto.DepartmentStats(
           e.department,
           COUNT(e)
       )
       FROM Employee e
       GROUP BY e.department
       ORDER BY COUNT(e) DESC
       """)
    List<DepartmentStats> getDepartmentStatistics();
    
    @Query("""
SELECT new com.linuka.employeehub.dto.SalaryStats(
    CASE
        WHEN e.salary < 3000 THEN 'Below 3000'
        WHEN e.salary BETWEEN 3000 AND 6000 THEN '3000 - 6000'
        WHEN e.salary BETWEEN 6001 AND 10000 THEN '6001 - 10000'
        ELSE 'Above 10000'
    END,
    COUNT(e)
)
FROM Employee e
GROUP BY
    CASE
        WHEN e.salary < 3000 THEN 'Below 3000'
        WHEN e.salary BETWEEN 3000 AND 6000 THEN '3000 - 6000'
        WHEN e.salary BETWEEN 6001 AND 10000 THEN '6001 - 10000'
        ELSE 'Above 10000'
    END
""")
    List<SalaryStats> getSalaryStatistics();



}