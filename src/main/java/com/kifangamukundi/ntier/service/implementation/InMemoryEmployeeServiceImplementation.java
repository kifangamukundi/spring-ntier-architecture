package com.kifangamukundi.ntier.service.implementation;

import com.kifangamukundi.ntier.model.Employee;
import com.kifangamukundi.ntier.repository.InMemoryEmployeeRepository;
import com.kifangamukundi.ntier.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

// Service layer contract implementation for in memory database

// Generates a constructor with required arguments.
// Required arguments are final fields and fields with constraints such as @NonNull
@RequiredArgsConstructor
@Service

// @Primary can be used to decide which bean to inject when ambiguity is present regarding dependency injection.
@Qualifier(value = "inMemory")
public class InMemoryEmployeeServiceImplementation implements EmployeeService {
    // Dependency Injection
    private final InMemoryEmployeeRepository inMemoryEmployeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return inMemoryEmployeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return inMemoryEmployeeRepository.getAllEmployees();
    }

    @Override
    public Employee findById(Integer id) {
        return inMemoryEmployeeRepository.findById(id);
    }

    // Assignment
    @Override
    public Employee updateEmployee(Integer id, Employee employee) {
        return employee;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return inMemoryEmployeeRepository.deleteById(id);
    }
}
