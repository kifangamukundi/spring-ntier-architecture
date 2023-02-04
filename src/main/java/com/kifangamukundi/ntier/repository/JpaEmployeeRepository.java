package com.kifangamukundi.ntier.repository;

import com.kifangamukundi.ntier.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT s FROM Employee s WHERE s.username=?1")
    Optional<Employee> findEmployeeByUserName(String username);
}
