package com.linuka.employeehub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.aspectj.bridge.IMessage;

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
    private Long id;

    @NotBlank(message = "First name is Required")
    @Size(min =2,message = "First name must at least have 2 characters")
    private String firstName;

    @NotBlank(message = "last name is Required")
    private String lastName;

    @NotBlank(message = "Email is Required")
    @Email(message = "Enter a valid Email Address")
    private String email;

    @NotBlank(message = "Contact Number is Required")
    private String phone;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Salary is Required")
    @Positive(message = "Salary must be Positive")
    private BigDecimal salary;      // BigDecimal is the preferred choice for money because it avoids floating-point precision issues

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;
}
