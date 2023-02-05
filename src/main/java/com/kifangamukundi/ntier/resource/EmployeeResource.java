package com.kifangamukundi.ntier.resource;


import com.kifangamukundi.ntier.model.Employee;
import com.kifangamukundi.ntier.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

// This is the Controller or the Resource Layer
// It exposes the available endpoints that can be called

// @RestController It's a convenient annotation that combines @Controller and @ResponseBody,
// which eliminates the need to annotate every request
// handling method of the controller class with the @ResponseBody
@RestController

// the annotation is used to map web requests to Spring Controller methods
@RequestMapping(path = "/api/v1/employees")
public class EmployeeResource {

    // Dependency Injection
    // Dependency Injection is a method through which the Spring container “injects”
    // objects into other objects or “dependencies”.

    //* Types *//

    // 1) Field Injection
    // Field Injection uses reflection to set the values of private variables
    //    @Autowired
    //    private UserService userService;

    // 2) Constructor Injection - lets the Spring Container inject the dependencies directly through constructor
    // Constructor Injection happens at the time of creating the object itself
    //    private final UserService userService;
    //    public UserController(UserService userService){
    //        this.userService = userService;
    //    }

    // 3) Setter Injection
    // Setter Injection uses setters to set the value
    //    private UserService userService;
    //    @Autowired
    //    public void setUserService(UserService userService){
    //        this.userService = userService;
    //    }


    private final EmployeeService employeeService;

    // @Qualifier is used to eliminate the issue of which bean needs to be injected
    // Helps to avoid ambiguity when Spring finds multiple beans of the same type
    // It helps us to specify which service implementation we would like to use in case of more than one
    // In this example we have two ( postgresEmployeeService or inMemory)
    public EmployeeResource(@Qualifier(value = "postgresEmployeeService") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Fetch all the employees
    @GetMapping
    // ResponseEntity represents the whole HTTP response: status code, headers, and body.
    // As a result, we can use it to fully configure the HTTP response.
    // Furthermore, ResponseEntity provides two nested builder interfaces:
    // HeadersBuilder and its sub interface, BodyBuilder.
    // Therefore, we can access their capabilities through the static methods of ResponseEntity.
    // Examples: ok(), accepted(), badRequest(), created(), <?> noContent(), <?> notFound()
    public ResponseEntity<List<Employee>> getEmployees () {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Fetch a specific employee depending on an id passed
    // @PathVariable annotation can be used to handle template variables in the request URI mapping,
    // and set them as method parameters.
    // we can have more than one path variable in our request URI for a controller method,
    // which also has multiple method parameters: @GetMapping("{id}/{name}")
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    // Create a new employee
    // @RequestBody annotation maps the HttpRequest body to a transfer or domain object,
    // enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
    // By default, the type we annotate with the @RequestBody annotation must correspond
    // to the JSON sent from our client-side controller
    @PostMapping
    // @Valid, it automatically bootstraps the default JSR 380 implementation —
    // Hibernate Validator — and validates the argument.
    public ResponseEntity<Employee> addEmployee (@RequestBody @Valid Employee employee) {
        //employee.setId(employeeService.getAllEmployees().size() + 1);
        return ResponseEntity.created(getLocation(employee.getId())).body(employeeService.addEmployee(employee));
    }

    // Delete an employee with a specific id passed
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteEmployee (@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

    // Update a specific employee
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    protected static URI getLocation(Integer id) {
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
