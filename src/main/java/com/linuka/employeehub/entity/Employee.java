package com.linuka.employeehub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity  // This Java class represents a table in the database.
@Table(name = "employees")  // This tells Hibernate to create (or use) a table named:
@Data
@NoArgsConstructor  //JPA requires a no-argument constructor to create entity objects.
@AllArgsConstructor //Creates a constructor with all fields.
                    /// public Employee(Long id, String firstName, ... ) {
                    ///     ...
                    /// }

public class Employee {

    @Id    // Marks the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // MySQL generates the ID automatically
    private long Id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String department;

    private String position;

    private BigDecimal salary;      // BigDecimal is the preferred choice for money because it avoids floating-point precision issues

    private LocalDate hireDate;
}
