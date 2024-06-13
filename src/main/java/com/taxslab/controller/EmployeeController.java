package com.taxslab.controller;

import com.taxslab.entity.Employee;
import com.taxslab.model.EmployeeTaxDetails;
import com.taxslab.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/createEmployee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping(value = "/emp/tax")
    public EmployeeTaxDetails getEmpTaxDetails(@RequestParam String empCode) {
        return employeeService.getTaxDetails(empCode);
    }
}
