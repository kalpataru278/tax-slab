package com.taxslab.service;

import com.taxslab.Exception.EmployeeNotFoundException;
import com.taxslab.entity.Employee;
import com.taxslab.model.EmployeeTaxDetails;
import com.taxslab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeTaxDetails getTaxDetails(String empCode) {
        // get Emp
        Employee employee = employeeRepository.findById(Integer.valueOf(empCode))
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Does not exist with emp code:" + empCode));
        // calculate Yearly salary
        double totalSalary = employee.getSalary() * 12;
        // calculate TaxAmount
        double taxAmount = taxAmount(totalSalary);
        // calculate cessAmount;
        double cessAmount = getCessAmount(totalSalary);
        return EmployeeTaxDetails.builder()
                .employeeCode(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .yearlySalary(totalSalary)
                .taxAmount(taxAmount)
                .cessAmount(cessAmount)
                .build();
    }

    private double taxAmount(double totalSalary) {
        double tax = 0;
        if (totalSalary > 250000 && totalSalary <= 500000) {
            tax = (totalSalary - 250000) * 0.05;
        } else if (totalSalary > 500000 && totalSalary <= 1000000) {
            tax = (250000 * 0.05) + ((totalSalary - 500000) * 0.10);
        } else if (totalSalary > 1000000) {
            tax = (250000 * 0.05) + (500000 * 0.10) + ((totalSalary - 1000000) * 0.20);
        }
        return tax;
    }

    private double getCessAmount(double totalSalary) {
        return totalSalary > 250000 ? (totalSalary - 250000) * 0.02 : 0;
    }
}

