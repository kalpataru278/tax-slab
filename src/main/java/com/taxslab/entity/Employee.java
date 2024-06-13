package com.taxslab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "EMPLOYEE")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "First Name can not be Empty.")
    @Size(min = 2, max = 30, message = "First Name must be between 2 and 30 char.")
    private String firstName;
    @NotBlank(message = "Last Name can not be Empty.")
    @Size(min = 1, max = 15, message = "Last Name must be between 1 and 15 char.")
    private String lastName;
    @Email(message = "Email should be valid address")
    private String email;
    @NotNull
    @Size(min = 1, max = 2)
    private List<String> phoneNumber;
    @NotNull(message = "DOJ can no be null")
    @PastOrPresent(message = "DOJ must be in past date or present date")
    private LocalDate doj;
    @NotNull(message = "Salary can not be Empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be > 0")
    private Double salary;
}
