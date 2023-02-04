package com.kifangamukundi.ntier.service.implementation;

import com.kifangamukundi.ntier.exception.custom.Errors;
import com.kifangamukundi.ntier.exception.custom.Exists;
import com.kifangamukundi.ntier.exception.global.ApplicationException;
import com.kifangamukundi.ntier.model.Employee;
import com.kifangamukundi.ntier.repository.JpaEmployeeRepository;
import com.kifangamukundi.ntier.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Service layer contract implementation for in postgres database

// Generates a constructor with required arguments.
// Required arguments are final fields and fields with constraints such as @NonNull
@RequiredArgsConstructor
@Service

// @Primary can be used to decide which bean to inject when ambiguity is present regarding dependency injection.
@Qualifier(value = "postgresEmployeeService")
public class JpaEmployeeServiceImplementation implements EmployeeService {

    // Dependency Injection
    private final JpaEmployeeRepository jpaEmployeeRepository;

    private final ModelMapper mapper;

    @Override
    public Employee addEmployee(Employee employee) throws ApplicationException {

        String username = employee.getUsername();
        Optional<Employee> exists = jpaEmployeeRepository.findEmployeeByUserName(username);

        if (exists.isPresent()) {
            throw new ApplicationException(Exists.RESOURCE_EXISTS, Map.of("id", username));
        }

        Employee result = jpaEmployeeRepository.save(mapper.map(employee, Employee.class));
        return mapper.map(result, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return jpaEmployeeRepository.findById(id)
                .map((f) -> mapper.map(f, Employee.class))
                .orElseThrow(() -> new ApplicationException(Errors.RESOURCE_NOT_FOUND, Map.of("id", id)));
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employee) {

        jpaEmployeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(Errors.RESOURCE_NOT_FOUND, Map.of("id", id)));
        employee.setId(id);
        Employee result = jpaEmployeeRepository.save(mapper.map(employee, Employee.class));
        return mapper.map(result, Employee.class);
    }

    @Override
    public Boolean deleteById(Integer id) {
        jpaEmployeeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(Errors.RESOURCE_NOT_FOUND, Map.of("id", id)));
        jpaEmployeeRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
