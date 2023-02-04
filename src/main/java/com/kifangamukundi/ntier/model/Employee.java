package com.kifangamukundi.ntier.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

// Lombok stuff
@Data // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor

// Entity stuff
@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_username_unique", columnNames = "username")
        }
)
public class Employee {

        @Id
        @SequenceGenerator(
                name = "employee_sequence",
                sequenceName = "employee_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "employee_sequence"
        )
        @Column(
                name = "id",
                updatable = false
        )
        private Integer id;

        // @NotBlank Suppose we are doing input validations
        @NotBlank
        @Column(
                name = "first_name",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String firstName;

        @NotBlank
        @Column(
                name = "last_name",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String lastName;

        @NotBlank
        @Column(
                name = "username",
                nullable = false,
                columnDefinition = "TEXT"
        )
        private String username;
}
