package com.backend.Library_Management_System.Entity;

import com.backend.Library_Management_System.Enum.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    // All the CRUD operation applied on student class will be
    // automatically called in librayCard class using CascadeType.ALL
     LibraryCard card;
}
