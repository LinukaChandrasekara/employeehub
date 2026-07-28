// WHY INTERFACE INSTEAD OF A CLASS?

// JpaRepository is an interface, and Spring Data JPA creates the implementation for you at runtime.
// That means you define what operations you need, and Spring provides how they're implemented.

package com.linuka.employeehub.repository;

import com.linuka.employeehub.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
