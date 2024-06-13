package com.taxslab.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeTaxDetails {
    private int employeeCode;
    private String firstName;
    private String lastName;
    private Double yearlySalary;
    private Double taxAmount;
    private Double cessAmount;
}
