package com.kifangamukundi.ntier.init;

import com.github.javafaker.Faker;
import com.kifangamukundi.ntier.model.Employee;
import com.kifangamukundi.ntier.repository.JpaEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Run everytime the application starts
// Saves dummy data to the database for testing purposes
@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    private final JpaEmployeeRepository jpaEmployeeRepository;


    @Override
    public void run(String ...args) throws Exception {

        Faker faker = new Faker();

        for (int i = 1; i <=20; i++) {
            Integer id = i;
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String username = firstName + lastName;

            Employee employee = new Employee(id, firstName, lastName, username);

            jpaEmployeeRepository.save(employee);
        }
    }
}
