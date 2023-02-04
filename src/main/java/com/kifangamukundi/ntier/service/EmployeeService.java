package com.kifangamukundi.ntier.service;

import com.kifangamukundi.ntier.model.Employee;

import java.util.List;

// Business layer service that defines a specification that needs to be handed by different implementations
// It is a contract or blueprint

/* Notes */
// interfaces cannot be used to create objects
// Interface methods do not have a body - the body is provided by the "implement" class
// On implementation of an interface, you must override all of its methods
// Interface methods are by default abstract and public
// Interface attributes are by default public, static and final
// An interface cannot contain a constructor (as it cannot be used to create objects)
public interface EmployeeService {
    // Save an employee
    Employee addEmployee(Employee employee);

    // Get employees

    List<Employee> getAllEmployees();

    // Get one employee
    Employee findById(Integer id);

    // Update employee
    Employee updateEmployee(Integer id, Employee employee);

    // Delete employee
    Boolean deleteById(Integer id);
}